package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.temporary.sampleLaunches
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.datasource.LaunchApiDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.temporary.sampleNews
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


    // DB

    fun applyLaunch(id: String) {
        viewModelScope.launch {
            _launchState.value = repository.getLaunchesById(id)
        }
    }

    val launches = repository.getAllLaunches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

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

    fun onEvent(event: LaunchesEvent) {
        when (event) {
            is LaunchesEvent.OnSearchQueryChange -> setSearchQuery(event.query)
            is LaunchesEvent.OnDownloadRequested -> downloadLaunches()
            is LaunchesEvent.OnClearDatabase -> clearDatabase()
        }
    }
}
