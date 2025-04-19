package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.sampleLaunches
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LaunchesDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(sampleLaunches)
    val state = _state.asStateFlow()

    private val _launchState = MutableStateFlow<Launch?>(null)
    val launchState = _launchState.asStateFlow()

    fun applyLaunch(id: String) {
        val launch = sampleLaunches.find { it.id == id }
        _launchState.update { launch }
    }
}