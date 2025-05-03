package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.formatLaunchDate
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.openUrl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events.LaunchesEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.OffsetDateTime
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf


@Composable
fun LaunchesScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    query: String,
    launches: List<Launch>,
    onEvent: (LaunchesEvent) -> Unit,
    onDetailsClick: (String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            LaunchesAppBar()
        },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LaunchContent(
            launches = launches,
            modifier = Modifier.padding(innerPadding),
            onDetailsClick = onDetailsClick,
            snackbarHostState = snackbarHostState,
            query = query,
            onEvent = onEvent,
        )
    }
}

@Composable
fun LaunchContent(
    launches: List<Launch>,
    modifier: Modifier = Modifier,
    onDetailsClick: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    query: String,
    onEvent: (LaunchesEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = query,
                onValueChange = { onEvent(LaunchesEvent.OnSearchQueryChange(it)) },
                label = { Text("Search by name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        items(launches) { launch ->
            LaunchItem(
                launch = launch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onDetailsClick = { onDetailsClick(launch.id) },
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

            CountdownTimer(netTime = launch.net, launch.status.abbrev)

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
                            url = launch.videoUrls.firstOrNull() ?: "",
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
fun CountdownTimer(
    netTime: String,
    status: String,
    modifier: Modifier = Modifier
) {

    val targetTime = remember(netTime) {
        OffsetDateTime.parse(netTime).toInstant()
    }

    var timeLeft by remember { mutableStateOf(Duration.between(Instant.now(), targetTime)) }

    val isPast by remember { derivedStateOf { timeLeft.isNegative || timeLeft.isZero } }

    LaunchedEffect(targetTime) {
        while (!isPast) {
            val now = Instant.now()
            timeLeft = Duration.between(now, targetTime)

            val millisToNextSecond = 1000 - (System.currentTimeMillis() % 1000)
            delay(millisToNextSecond)
        }
    }

    if (isPast) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFB3CCAC),
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = status,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    } else {
        val totalSeconds = timeLeft.seconds
        val days = totalSeconds / (60 * 60 * 24)
        val hours = (totalSeconds / (60 * 60)) % 24
        val minutes = (totalSeconds / 60) % 60
        val seconds = totalSeconds % 60

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "T-",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "${days.toString().padStart(2, '0')} : ${
                        hours.toString().padStart(2, '0')
                    } : ${minutes.toString().padStart(2, '0')} : ${
                        seconds.toString().padStart(2, '0')
                    }",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                listOf("Days", "Hours", "Mins", "Secs").forEach {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesAppBar() {
    TopAppBar(
        title = { Text("Launches") },
    )
}