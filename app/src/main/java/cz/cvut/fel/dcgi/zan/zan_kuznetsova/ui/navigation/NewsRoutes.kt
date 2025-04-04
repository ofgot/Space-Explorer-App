package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NewsRoutes  {
    @Serializable
    data object News : NewsRoutes

    @Serializable
    data object NewsDetails : NewsRoutes
}