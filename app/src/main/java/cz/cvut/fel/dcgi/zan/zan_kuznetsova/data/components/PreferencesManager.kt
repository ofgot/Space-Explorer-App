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
        private val LAST_LAUNCH_UPDATE = longPreferencesKey("last_launch_update")
        private const val INTERVAL_MILLIS = 1 * 60 * 1000L
    }

    suspend fun shouldReload(): Boolean {
        return try {
            val lastUpdate = context.dataStore.data
                .map { prefs -> prefs[LAST_LAUNCH_UPDATE] ?: 0L }
                .first()

            val now = System.currentTimeMillis()
            val shouldReload = (now - lastUpdate) > INTERVAL_MILLIS

            Log.e( "RELOAD","Checked reload: now=$now, lastUpdate=$lastUpdate, shouldReload=$shouldReload")

            shouldReload
        } catch (e: Exception) {
            Log.e("RELOAD", "Error checking reload condition", e)
            true
        }
    }

    suspend fun saveReloadTime() {
        context.dataStore.edit { prefs ->
            prefs[LAST_LAUNCH_UPDATE] = System.currentTimeMillis()
        }
    }
}