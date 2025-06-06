package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import SettingsViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
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
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.LaunchViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.NewsViewModel
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events.NewsEvent
import androidx.compose.ui.platform.LocalContext
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.components.PreferencesManager
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events.LaunchesEvent


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
                val viewModel =
                    backStackEntry.sharedKoinNavViewModel<LaunchViewModel>(navController)

                val query by viewModel.searchQuery.collectAsState()
                val filteredLaunches by viewModel.filteredLaunches.collectAsStateWithLifecycle()

                val allLaunches by viewModel.launches.collectAsStateWithLifecycle()

                val context = LocalContext.current

//                viewModel.clearDatabase()
                LaunchedEffect(allLaunches) {

                    if (allLaunches.isEmpty()) {
                        viewModel.onEvent(
                            LaunchesEvent.OnDownloadRequested,
                            context
                        )
                    }
                }

                val route = currentBackStackEntry.value?.destination?.route.orEmpty()

                LaunchesScreen(
                    mainBottomNavigationItems = mainBottomNavItem,
                    currentDestination = route,
                    query = query,
                    launches = filteredLaunches,
                    onEvent = { event ->
                        viewModel.onEvent(event, context)
                    },
                    onDetailsClick = { id ->
                        viewModel.applyLaunch(id)
                        navController.navigate(LaunchesRoutes.LaunchDetails)
                    },
                )
            }

            composable<LaunchesRoutes.LaunchDetails> { backStackEntry ->
                val viewModel =
                    backStackEntry.sharedKoinNavViewModel<LaunchViewModel>(navController)
                val launchState by viewModel.launchState.collectAsStateWithLifecycle()

                launchState?.let { state ->
                    LaunchDetailsScreen(
                        launch = state,
                        onBackClick = { navController.popBackStack() },
                    )
                }
            }
        }

        navigation<Routes.NewsGraph>(
            startDestination = NewsRoutes.News
        ) {
            composable<NewsRoutes.News> { backStackEntry ->
                val viewModel = backStackEntry.sharedKoinNavViewModel<NewsViewModel>(navController)

                val query by viewModel.searchQuery.collectAsState()
                val filteredNews by viewModel.filteredNews.collectAsStateWithLifecycle()

                val route = currentBackStackEntry.value?.destination?.route.orEmpty()

                val selectedNewsIds by viewModel.selectedNewsIds.collectAsState()

                NewsScreen(
                    mainBottomNavigationItems = mainBottomNavItem,
                    currentDestination = route,
                    query = query,
                    news = filteredNews,
                    onEvent = viewModel::onEvent,
                    onDetailsClick = { id ->
                        viewModel.applyNews(id)
                        navController.navigate(NewsRoutes.NewsDetails)
                    },

                    selectedNewsIds = selectedNewsIds,
                )
            }

            composable<NewsRoutes.NewsDetails> { backStackEntry ->
                val viewModel = backStackEntry.sharedKoinNavViewModel<NewsViewModel>(navController)
                val newsState by viewModel.newsState.collectAsStateWithLifecycle()

                // Comment
                val comment by viewModel.commentState.collectAsStateWithLifecycle()
                val isEditing by viewModel.isEditing.collectAsStateWithLifecycle()

                newsState?.let {
                    NewsDetailsScreen(
                        news = it,
                        onBackClick = {
                            navController.popBackStack()
                            viewModel.onEvent(NewsEvent.OnEditingFinished)
                        },

                        // Comment
                        comment = comment,
                        isEditing = isEditing,
                        onClick = viewModel::onEvent,
                    )
                }
            }
        }

        composable<Routes.Settings>{ backStackEntry ->
            val viewModel = backStackEntry.sharedKoinNavViewModel<SettingsViewModel>(navController)
            val notificationsEnabled by viewModel.notificationsEnabled.collectAsState()

            SettingsScreen(
                mainBottomNavItem,
                currentBackStackEntry.value?.destination?.route,
                notificationsEnabled = notificationsEnabled,
                onToggleNotifications = viewModel::toggleNotifications
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