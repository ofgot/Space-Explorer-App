package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components
import android.content.Context

fun shouldReload(context: Context): Boolean {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val lastUpdate = prefs.getLong("last_launch_update", 0L)
    val now = System.currentTimeMillis()
    val oneDayMillis = 2 * 60 * 1000L  // 24 * 60 * 60 * 1000L - 1 day
    return (now - lastUpdate) > oneDayMillis
}

fun saveReloadTime(context: Context) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putLong("last_launch_update", System.currentTimeMillis()).apply()
}
