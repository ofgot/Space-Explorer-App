package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components.saveReloadTime
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components.shouldReload
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.datasource.LaunchApiDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events.LaunchesEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.*

class LaunchViewModel(
    private val repository: LaunchRepository,
    private val remoteDataSource: LaunchApiDataSource
) : ViewModel() {

    // States

    private val _launchState = MutableStateFlow<Launch?>(null)
    val launchState = _launchState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    // DB

    fun applyLaunch(id: String) {
        viewModelScope.launch {
            _launchState.value = repository.getLaunchesById(id)
        }
    }

    val launches = repository.getAllLaunches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun refresh(context: Context) {
        viewModelScope.launch {
            if (!shouldReload(context)) {
                Log.i("REFRESH", "Skip: Too soon to reload.")
                return@launch
            }

            _isRefreshing.value = true
            try {
                val launches = remoteDataSource.getLaunches()
                repository.insertLaunches(launches)
                saveReloadTime(context)
                Log.i("REFRESH", "Launches reloaded.")
            } catch (e: Exception) {
                Log.e("REFRESH", "API failed: ${e.message}")
            } finally {
                _isRefreshing.value = false
            }
        }
    }


    fun downloadLaunches() {
        viewModelScope.launch {
            if (!repository.hasLaunches()) {
                val launches = remoteDataSource.getLaunches()
                repository.insertLaunches(launches)
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.deleteAllLaunches()
        }
    }

    // Search

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val filteredLaunches = combine(launches, searchQuery) { list, query ->
        if (query.isBlank()) list
        else list.filter { it.name.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())


    // Events

    fun onEvent(
        event: LaunchesEvent, context: Context
    ) {
        when (event) {
            is LaunchesEvent.OnSearchQueryChange -> setSearchQuery(event.query)
            is LaunchesEvent.OnDownloadRequested -> downloadLaunches()
            is LaunchesEvent.OnClearDatabase -> clearDatabase()
            is LaunchesEvent.OnRefreshRequested -> refresh(context)
        }
    }
}
