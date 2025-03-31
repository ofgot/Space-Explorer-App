package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface LaunchesRoutes {

    @Serializable
    data object Launches : LaunchesRoutes

    @Serializable
    data object LaunchDetails : LaunchesRoutes

}