package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.NavigationItems.navItems
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme

@Composable
fun LaunchesScreen() {
    Scaffold(
        topBar = { LaunchesAppBar() },
        bottomBar = { LaunchesBottomNavigation() }, // ask if it's ok with ButtonNavItem and BottomNavigation
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LaunchContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LaunchesBottomNavigation() {
    BottomNavigation.BottomNavigation(
        items = navItems,
        selectedItemIndex = 0,
        onItemSelected = { index ->  }
    )
}

@Composable
fun LaunchContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(4) {
            LaunchItem(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun LaunchItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Rocket",
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.CenterVertically),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleLineText.SingleLineText(
                text = "Kuaizhou-1A | Unknown Payload",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            SingleLineText.SingleLineText(
                text = "ExPace",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            SingleLineText.SingleLineText(
                text = "Jiuquan Satellite Launch Center, People's Republic of China",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            CountdownTimer()
            SingleLineText.SingleLineText(
                "Day Hours Min Secs", MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText.SingleLineText(
                "March 01, 2025 - NET 11:00 CET",
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Row(

            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.img_1),
                        contentDescription = "Watch video",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.img_2),
                        contentDescription = "See details",
                        modifier = Modifier.size(35.dp),
                    )
                }
            }

        }
    }
}

@Composable
fun CountdownTimer(modifier: Modifier = Modifier) {
    val days = 0
    val hours = 0
    val minutes = 0
    val seconds = 0

    val isPast = true

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SingleLineText.SingleLineText(
                text = "T${if (isPast) "+" else "-"}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 4.dp)
            )
            SingleLineText.SingleLineText(
                text = "${days.toString().padStart(2, '0')} : " +
                        "${hours.toString().padStart(2, '0')} : " +
                        "${minutes.toString().padStart(2, '0')} : " +
                        "${seconds.toString().padStart(2, '0')}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesAppBar() {
    TopAppBar(
        title = {
            Text(text = "Launches")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserProfileScreenPreview() {
    ZankuznetsovaTheme {
        LaunchesScreen()
    }
}