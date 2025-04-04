package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object LaunchesGraph : Routes

    @Serializable
    data object News : Routes

    @Serializable
    data object Settings : Routes

    @Serializable
    data object NewsGraph : Routes
}