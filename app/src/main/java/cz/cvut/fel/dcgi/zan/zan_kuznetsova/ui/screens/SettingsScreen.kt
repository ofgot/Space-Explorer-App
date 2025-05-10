package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem

@Composable
fun SettingsScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    notificationsEnabled: Boolean,
    onToggleNotifications: (Boolean) -> Unit
) {
    Scaffold(
        topBar = { SettingsAppBar() },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SettingsContent(
            modifier =  Modifier.padding(innerPadding),
            notificationsEnabled = notificationsEnabled,
            onToggleNotifications = onToggleNotifications
        )
    }
}

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    notificationsEnabled: Boolean,
    onToggleNotifications: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Launch notifications",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = onToggleNotifications
            )
        }
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

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        mainBottomNavigationItems = emptyList(),
        currentDestination = null,
        notificationsEnabled = true,
        onToggleNotifications = {}
    )
}

