package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconId: Int,
    val contentDescription: String,
    val onClick: () -> Unit
)
