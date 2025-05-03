package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components

import android.content.Context

class PreferencesManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun shouldReload(): Boolean {
        val lastUpdate = prefs.getLong("last_launch_update", 0L)
        val now = System.currentTimeMillis()
        val intervalMillis = 30 * 60 * 1000L  // 30 minutes
        return (now - lastUpdate) > intervalMillis
    }

    fun saveReloadTime() {
        prefs.edit().putLong("last_launch_update", System.currentTimeMillis()).apply()
    }
}
