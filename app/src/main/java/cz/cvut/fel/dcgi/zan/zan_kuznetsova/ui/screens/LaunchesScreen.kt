package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Query
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Agency
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Status
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.formatLaunchDate
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.openUrl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem
import kotlinx.coroutines.launch

@Composable
fun LaunchesScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    launches: List<Launch>,
    onDetailsClick: (String) -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    var query by remember { mutableStateOf("") }

    val filteredLaunches by remember(query, launches) {
        derivedStateOf {
            if (query.isBlank()) launches
            else launches.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            Column {
                LaunchesAppBar(query, onQueryChange = { query = it })
            }
        },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LaunchContent(
            launches = filteredLaunches,
            modifier = Modifier.padding(innerPadding),
            onDetailsClick = onDetailsClick,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun LaunchContent(
    launches: List<Launch>,
    modifier: Modifier = Modifier,
    onDetailsClick: (String) -> Unit,
    snackbarHostState: SnackbarHostState
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
                },
                snackbarHostState = snackbarHostState
            )
        }
    }
}


@Composable
fun LaunchItem(
    launch: Launch,
    modifier: Modifier = Modifier,
    onDetailsClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = launch.image?.url,
            contentDescription = launch.image?.name,
            modifier = Modifier
                .padding(top = 15.dp)
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

            SingleLineText(
                text = launch.agency?.name.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

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
                IconButton(onClick = {
                    scope.launch {
                        openUrl(
                            snackbarHostState = snackbarHostState,
                            context = context,
                            url = launch.webcastLive,
                            message = "Video not available"
                        )
                    }
                }) {
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
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
            SingleLineText(
                text = "T${if (isPast) "+" else "-"}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 4.dp)
            )
            SingleLineText(
                text = "${days.toString().padStart(2, '0')} : " + "${
                    hours.toString().padStart(2, '0')
                } : " + "${minutes.toString().padStart(2, '0')} : " + "${
                    seconds.toString().padStart(2, '0')
                }", style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            SingleLineText(
                "Days",
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Hours",
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Mins",
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )
            SingleLineText(
                "Secs",
                MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 20.dp)
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesAppBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column {
        TopAppBar(title = {
            Text(text = "Launches")
        })

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Search by name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaunchItem() {
    val fakeLaunch = Launch(
        id = "1",
        name = "Falcon 9 | Starlink Group 7-2",
        status = Status(name = "Success", abbrev = "S"),
        net = "2025-04-01T15:30:00Z",
        location = "Cape Canaveral",
        webcastLive = "https://youtube.com",
        image = Image(
            name = "Falcon 9",
            url = "https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/spectrum_on_the_image_20250321072643.jpeg"
        ),
        agency = Agency(
            name = "SpaceX",
            abbrev = "SPX",
            country = "USA",
            description = "",
            logo = null,
            totalLaunchCount = 0,
            successfulLaunches = 0,
            failedLaunches = 0,
            wikiUrl = "",
            infoUrl = ""
        ),
        rocket = null
    )

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchItem(
        launch = fakeLaunch,
        onDetailsClick = {},
        snackbarHostState = snackbarHostState,
        modifier = Modifier.padding(8.dp)
    )
}
