package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedNavViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(viewModelStoreOwner = parentEntry)
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinNavViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}


