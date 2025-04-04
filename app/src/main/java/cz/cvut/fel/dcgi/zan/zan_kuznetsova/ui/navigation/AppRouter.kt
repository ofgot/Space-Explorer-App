package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.LaunchDetailsScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.LaunchesScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.NewsDetailsScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.NewsScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.SettingsScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.LaunchesDetailsViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.NewsDetailsViewModel


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
                route = Routes.LaunchesGraph,
                label = "Launches",
                iconId = R.drawable.rocket,
                contentDescription = "Launches nav bar item",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.LaunchesGraph)
                }
            ),
            BottomNavItem(
                route = Routes.News,
                label = "News",
                iconId = R.drawable.news,
                contentDescription = "News nav bar item",
                onClick = {
                    navigateToBottomNavItem(navController, Routes.NewsGraph)
                }
            ),
            BottomNavItem(
                route = Routes.Settings,
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
        startDestination = Routes.LaunchesGraph
    ) {

        navigation<Routes.LaunchesGraph>(
            startDestination = LaunchesRoutes.Launches
        ) {
            composable<LaunchesRoutes.Launches> { backStackEntry ->
                val viewModel = backStackEntry.sharedNavViewModel<LaunchesDetailsViewModel>(navController)
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchesScreen(
                    mainBottomNavItem,
                    currentBackStackEntry.value?.destination?.route,
                    state,
                    onDetailsClick = { id ->
                        viewModel.applyLaunch(id)
                        navController.navigate(LaunchesRoutes.LaunchDetails)
                    }
                )
            }

            composable<LaunchesRoutes.LaunchDetails> { backStackEntry ->
                val viewModel = backStackEntry.sharedNavViewModel<LaunchesDetailsViewModel>(navController)
                val launchState by viewModel.launchState.collectAsStateWithLifecycle()

                launchState?.let { state ->
                    LaunchDetailsScreen(
                        launch = state,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }

        navigation<Routes.NewsGraph>(
            startDestination = NewsRoutes.News
        ) {
            composable<NewsRoutes.News> { backStackEntry ->
                val viewModel = backStackEntry.sharedNavViewModel<NewsDetailsViewModel>(navController)
                val state by viewModel.state.collectAsStateWithLifecycle()

                NewsScreen(
                    mainBottomNavigationItems = mainBottomNavItem,
                    currentDestination = currentBackStackEntry.value?.destination?.route,
                    news = state,
                    onDetailsClick = { id ->
                        viewModel.applyNews(id)
                        navController.navigate(NewsRoutes.NewsDetails)
                    }
                )
            }

            composable<NewsRoutes.NewsDetails> { backStackEntry ->
                val viewModel = backStackEntry.sharedNavViewModel<NewsDetailsViewModel>(navController)
                val newsState by viewModel.newsState.collectAsStateWithLifecycle()

                newsState?.let {
                    NewsDetailsScreen(
                        news = it,
                        onBackClick = { navController.popBackStack() })
                }
            }
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