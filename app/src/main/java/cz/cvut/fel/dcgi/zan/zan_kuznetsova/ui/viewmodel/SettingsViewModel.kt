import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val value = preferencesManager.isNotificationsEnabled()
                Log.e("TOGGLE", "Loaded from DataStore: $value")
                _notificationsEnabled.value = value
            } catch (e: Exception) {
                _notificationsEnabled.value = true
            }
        }
    }

    fun toggleNotifications(enabled: Boolean) {
        _notificationsEnabled.value = enabled
        Log.e("TOGGLE", "Switch toggled: $enabled")
        viewModelScope.launch {
            try {
                preferencesManager.setNotificationsEnabled(enabled)
                Log.e("TOGGLE", "Saved to DataStore: $enabled")
            } catch (e: Exception) {
                Log.e("TOGGLE", "Error saving: ${e.message}")
            }
        }
    }
}

