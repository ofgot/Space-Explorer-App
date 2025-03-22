package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.sampleLaunches
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.LaunchesScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.NewsScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.SettingsScreen


@Composable
fun AppRouter() {
    val navController = rememberNavController()

    MainAppRouter(
        navController = navController,
    )
}

@Composable
fun MainAppRouter(navController: NavHostController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    val mainBottomNavItem = remember {
        listOf(
            BottomNavItem(
                route = Routes.Launches.toString(),
                label = "Launches",
                iconId = R.drawable.rocket,
                contentDescription = "Launches nav bar item",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.Launches)
                }
            ),
            BottomNavItem(
                route = Routes.News.toString(),
                label = "News",
                iconId = R.drawable.news,
                contentDescription = "News nav bar item",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.News)
                }
            ),
            BottomNavItem(
                route = Routes.Settings.toString(),
                label = "Settings",
                iconId = R.drawable.settings,
                contentDescription = "Settings nav bar item",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.Settings)
                }
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Launches
    ){
        // ask what to do with Launches
        composable<Routes.Launches>() {
            LaunchesScreen(
                mainBottomNavItem,
                currentBackStackEntry.value?.destination?.route,
                sampleLaunches
            )
        }
        composable<Routes.News>() {
            NewsScreen(
                mainBottomNavItem,
                currentBackStackEntry.value?.destination?.route,
            )
        }
        composable<Routes.Settings>() {
            SettingsScreen(
                mainBottomNavItem,
                currentBackStackEntry.value?.destination?.route,
            )
        }

    }
}


fun navigateToBottomNavItem(navController: NavHostController, route: Routes) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}