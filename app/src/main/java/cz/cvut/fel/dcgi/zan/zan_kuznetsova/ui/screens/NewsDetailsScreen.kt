package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText

@Composable
fun NewsDetailsScreen(
    news: News,
    onBackClick: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { DetailsAppBar(onBackClick) },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        NewsDetailsContent(
            news,
            modifier =  Modifier.padding(innerPadding),
            snackbarHostState = snackbarHostState
        )
    }
}



@Composable
fun NewsDetailsContent(
    news: News,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NewsDetailsItem(
            news,
            modifier = Modifier.padding(top = 4.dp),
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun NewsDetailsItem(
    news: News,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    val scrollableColumnState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .verticalScroll(scrollableColumnState)
    ) {
        SingleLineText(
            text = news.title,
            style = MaterialTheme.typography.titleLarge,
        )

        AsyncImage(
            model = news.image?.url,
            contentDescription = news.image?.name,
            modifier = Modifier
                .padding(20.dp)
                .height(300.dp)
        )

        SingleLineText(
            text = news.summary,
            style = MaterialTheme.typography.bodyLarge,
        )
    }

}