package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R

object NavigationItems {
    val navItems = listOf(
        BottomNavItem(R.drawable.rocket, "Launches", "Launches nav bar item"),
        BottomNavItem(R.drawable.news, "News", "News nav bar item"),
        BottomNavItem(R.drawable.settings, "Settings", "Settings nav bar item")
    )
}