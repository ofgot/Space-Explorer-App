package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    companion object {
        private const val INTERVAL_MILLIS = 30 * 60 * 1000L
    }

    private fun keyFor(name: String) = longPreferencesKey("last_update_$name")

    suspend fun shouldReload(name: String): Boolean {
        return try {
            val key = keyFor(name)
            val lastUpdate = context.dataStore.data
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
            context.dataStore.edit { prefs ->
                prefs[key] = System.currentTimeMillis()
            }
            Log.e("RELOAD", "[$name] Reload time saved successfully")
        } catch (e: Exception) {
            Log.e("RELOAD", "[$name] Error saving reload time", e)
        }
    }
}