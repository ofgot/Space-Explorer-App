package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "app_prefs")

class PreferencesManager(private val context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    // reload launches/news
    companion object {
        private const val INTERVAL_MILLIS = 30 * 60 * 1000L
        private val NOTIFICATIONS_ENABLED_KEY = booleanPreferencesKey("notifications_enabled")

    }

    private fun keyFor(name: String) = longPreferencesKey("last_update_$name")

    suspend fun shouldReload(name: String): Boolean {
        return try {
            val key = keyFor(name)
            val lastUpdate = dataStore.data
                .map { prefs -> prefs[key] ?: 0L }
                .first()

            val now = System.currentTimeMillis()
            val shouldReload = (now - lastUpdate) > INTERVAL_MILLIS

            Log.e("RELOAD", "[$name] Checked reload: now=$now, lastUpdate=$lastUpdate, shouldReload=$shouldReload")

            shouldReload
        } catch (e: Exception) {
            Log.e("RELOAD", "[$name] Error checking reload condition", e)
            true
        }
    }

    suspend fun saveReloadTime(name: String) {
        try {
            val key = keyFor(name)
            dataStore.edit { prefs ->
                prefs[key] = System.currentTimeMillis()
            }
            Log.e("RELOAD", "[$name] Reload time saved successfully")
        } catch (e: Exception) {
            Log.e("RELOAD", "[$name] Error saving reload time", e)
        }
    }

    // notifications
    suspend fun isNotificationsEnabled(): Boolean {
        return dataStore.data
            .map { it[NOTIFICATIONS_ENABLED_KEY] ?: true }
            .first()
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[NOTIFICATIONS_ENABLED_KEY] = enabled
        }
    }

}