package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem

@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    currentRoute: String?,
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = item.contentDescription,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = { Text(item.label) },
                selected = item.route.toString() == currentRoute,
                onClick = item.onClick
            )
        }
    }
}
