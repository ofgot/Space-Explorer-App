package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem

@Composable
fun NewsScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
) {
    Scaffold(
        topBar = { NewsAppBar() },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NewsContent(
            modifier =  Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NewsContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // {ToDO}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar() {
    TopAppBar(
        title = {
            Text(text = "News")
        },
    )
}
