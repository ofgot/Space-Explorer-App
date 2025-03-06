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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme

@Composable
fun DetailsScreen() {
    Scaffold(
        topBar = { DetailsAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        DetailsContent(
            modifier =  Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun DetailsContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DetailsItem(modifier = Modifier.padding(top = 4.dp))
    }
}


@Composable
fun DetailsItem(modifier: Modifier = Modifier) {
    val scrollableColumnState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .verticalScroll(scrollableColumnState)
    ) {
        SingleLineText.SingleLineText(
            text = "Kuaizhou-1A | Unknown Payload",
            style = MaterialTheme.typography.titleLarge,
        )
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Rocket",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .height(300.dp)
        )
        DetailsTextField(
            text1 = "Height",
            text2 = "19.40 Meters",
            style = MaterialTheme.typography.bodyLarge,
        )
        DetailsTextField(
            text1 = "Max Stages",
            text2 = "4",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Mass To GTO",
            text2 = "0 kg",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Liftoff Thrust",
            text2 = "0 kN",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Diameter",
            text2 = "1.40 Meters",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Mass To LEO",
            text2 = "300 kg",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Liftoff Mass",
            text2 = "30 Tonnes",
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        DetailsTextField(
            text1 = "Launch Success",
            text2 = "26",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Consecutive Success",
            text2 = "14",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Maiden Flight",
            text2 = "2017-01-09",
            style = MaterialTheme.typography.bodyLarge
        )
        DetailsTextField(
            text1 = "Launch Failures",
            text2 = "2",
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.img_5),
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
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SingleLineText.SingleLineText(
            text = text1,
            style = style,
            modifier = Modifier.weight(1f)
        )
        SingleLineText.SingleLineText(
            text = text2,
            style = style
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar() {
    TopAppBar(
        title = {
            Text(text = "Details")
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Navigation back",
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    ZankuznetsovaTheme {
        DetailsScreen()
    }
}