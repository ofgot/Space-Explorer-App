package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.NavigationItems.navItems
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme


@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = { FavoriteAppBar() },
        bottomBar = { FavoriteBottomNavigation() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SettingsContent(
            modifier =  Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FavoriteContent(modifier: Modifier = Modifier) {
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
fun FavoriteAppBar() {
    TopAppBar(
        title = {
            Text(text = "Favorite")
        },
    )
}

@Composable
fun FavoriteBottomNavigation() {
    BottomNavigation.BottomNavigation(
        items = navItems,
        selectedItemIndex = 1,
        onItemSelected = { index ->  }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    ZankuznetsovaTheme {
        FavoriteScreen()
    }
}