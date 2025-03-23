package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.formatLaunchDate
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.navigateToBottomNavItem
import java.net.URLEncoder

@Composable
fun LaunchesScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    launches: List<Launch>,
    onDetailsClick: (String) -> Unit
) {
    Scaffold(
        topBar = { LaunchesAppBar() },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LaunchContent(
            launches = launches,
            modifier = Modifier.padding(innerPadding),
            onDetailsClick = onDetailsClick
        )
    }
}

@Composable
fun LaunchContent(
    launches: List<Launch>,
    modifier: Modifier = Modifier,
    onDetailsClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(launches) { launch ->
            LaunchItem(
                launch = launch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onDetailsClick = {
                    onDetailsClick(launch.id)
                }
            )
        }
    }
}

@Composable
fun LaunchItem(
    launch: Launch,
    modifier: Modifier = Modifier,
    onDetailsClick: () -> Unit
) {

    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = launch.image.url,
            contentDescription = launch.image.name,
            modifier = Modifier
                .width(100.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleLineText(
                text = launch.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            launch.agency?.let {
                SingleLineText(
                    text = it.name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            SingleLineText(
                text = launch.location,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            CountdownTimer()

            SingleLineText(
                formatLaunchDate(launch.net),
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Row(

            ) {
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launch.webcastLive))
                        context.startActivity(intent)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.play),
                        contentDescription = "Watch video",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }
                IconButton(
                    onClick = onDetailsClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.info),
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
            SingleLineText(
                text = "T${if (isPast) "+" else "-"}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 4.dp)
            )
            SingleLineText(
                text = "${days.toString().padStart(2, '0')} : " +
                        "${hours.toString().padStart(2, '0')} : " +
                        "${minutes.toString().padStart(2, '0')} : " +
                        "${seconds.toString().padStart(2, '0')}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            SingleLineText(
                "Days", MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Hours", MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Mins", MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Secs", MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
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
