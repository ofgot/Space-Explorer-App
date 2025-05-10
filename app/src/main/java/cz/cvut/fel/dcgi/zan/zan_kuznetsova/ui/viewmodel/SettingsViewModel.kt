package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(

) : ViewModel() {
    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled

    fun toggleNotifications(enabled: Boolean) {
        _notificationsEnabled.value = enabled
    }
}
