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
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.NavigationItems.navItems
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.utils.BottomNavigation

@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = { SettingsAppBar() },
        bottomBar = { SettingsBottomNavigation() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SettingsContent(
            modifier =  Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
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
fun SettingsAppBar() {
    TopAppBar(
        title = {
            Text(text = "Settings")
        },
    )
}

@Composable
fun SettingsBottomNavigation() {
    BottomNavigation(
        items = navItems,
        selectedItemIndex = 2,
        onItemSelected = { index ->  }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingScreenPreview() {
    ZankuznetsovaTheme {
        SettingsScreen()
    }
}
