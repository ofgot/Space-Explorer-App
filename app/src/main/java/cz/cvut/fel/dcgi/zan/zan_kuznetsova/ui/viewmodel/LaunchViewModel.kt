package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.temporary.sampleLaunches
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository.LaunchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.*

class LaunchViewModel(
    private val repository: LaunchRepository
) : ViewModel() {

    private val _launchState = MutableStateFlow<Launch?>(null)
    val launchState = _launchState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun applyLaunch(id: String) {
        viewModelScope.launch {
            _launchState.value = repository.getLaunchesById(id)
        }
    }

    val launches = repository.getAllLaunches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun downloadLaunches() {
        viewModelScope.launch {
            repository.insertLaunches(sampleLaunches)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.deleteAllLaunches()
        }
    }


    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }


    val filteredLaunches = combine(launches, searchQuery) { list, query ->
        if (query.isBlank()) list
        else list.filter { it.name.contains(query, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())


}
