package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.openUrl
import kotlinx.coroutines.launch

@Composable
fun LaunchDetailsScreen(
    launch: Launch,
    onBackClick: () -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { DetailsAppBar(onBackClick) },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        LaunchDetailsContent(
            launch,
            modifier =  Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState,
        )
    }
}

@Composable
fun LaunchDetailsContent(
    launch: Launch,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LaunchDetailsItem(
            launch,
            modifier = Modifier.padding(top = 4.dp).wrapContentHeight(),
            snackbarHostState = snackbarHostState
        )
    }
}


@Composable
fun LaunchDetailsItem(
    launch: Launch,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    val scrollableColumnState = rememberScrollState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .verticalScroll(scrollableColumnState)
    ) {
        SingleLineText(
            text = launch.name,
            style = MaterialTheme.typography.titleLarge,
        )
        AsyncImage(
            model = launch.image?.url,
            contentDescription = launch.image?.name,
            modifier = Modifier
                .padding(20.dp)
                .height(300.dp)
        )
        DetailsTextField(
            text1 = "Height",
            text2 = launch.rocket?.rocketDetails?.height.toString(),
            text3 = " meters",
            style = MaterialTheme.typography.bodyLarge,
        )
        DetailsTextField(
            text1 = "Max Stages",
            text2 = launch.rocket?.rocketDetails?.maxStage.toString(),
            text3 = "",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Mass To GTO",
            text2 = launch.rocket?.rocketDetails?.massToGTO.toString(),
            text3 = " kg",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Liftoff Thrust",
            text2 = launch.rocket?.rocketDetails?.liftoffThrust.toString(),
            text3 = " kN",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Diameter",
            text2 = launch.rocket?.rocketDetails?.diameter.toString(),
            text3 = " meters",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Mass To LEO",
            text2 = launch.rocket?.rocketDetails?.massToLEO.toString(),
            text3 = " kg",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Liftoff Mass",
            text2 = launch.rocket?.rocketDetails?.liftoffMass.toString(),
            text3 = " tonnes",
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        DetailsTextField(
            text1 = "Launch Success",
            text2 = launch.rocket?.rocketDetails?.successfulLaunches.toString(),
            text3 = "",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Maiden Flight",
            text2 = launch.rocket?.rocketDetails?.maidenFlight.toString(),
            text3 = "",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Launch Failures",
            text2 = launch.rocket?.rocketDetails?.failedLaunches.toString(),
            text3 = "",
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = {
                scope.launch {
                    openUrl(
                        snackbarHostState = snackbarHostState,
                        context = context,
                        url = launch.rocket?.rocketDetails?.wikiUrl,
                        message = "Wiki not available"
                    )
                }
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.wiki),
                contentDescription = "Wiki page",
                modifier = Modifier.size(35.dp),
            )
        }
    }
}

@Composable
fun DetailsTextField(
    text1: String,
    text2: String,
    text3: String,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SingleLineText(
            text = text1,
            style = style,
            modifier = Modifier.weight(1f)
        )
        SingleLineText(
            text = text2,
            style = style
        )
        SingleLineText(
            text = text3,
            style
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Details")
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Navigation back",
                )
            }
        }
    )
}
