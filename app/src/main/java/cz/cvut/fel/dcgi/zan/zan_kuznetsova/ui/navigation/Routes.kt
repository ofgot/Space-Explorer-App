package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {
    @Serializable
    data object Launches : Routes("Launches")
    @Serializable
    data object News : Routes("News")
    @Serializable
    data object Settings : Routes("Settings")
}