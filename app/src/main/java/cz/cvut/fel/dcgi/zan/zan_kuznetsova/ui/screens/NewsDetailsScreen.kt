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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.db.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.MoreLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.openUrl
import kotlinx.coroutines.launch

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
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollableColumnState)
    ) {
        MoreLineText(
            text = news.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            maxLine = Int.MAX_VALUE,
            textAlign = TextAlign.Center,
        )

        AsyncImage(
            model = news.image?.url,
            contentDescription = news.image?.name,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        MoreLineText(
            text = news.summary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            maxLine = Int.MAX_VALUE,
            textAlign = TextAlign.Center,
        )

        Row (
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            IconButton(
                onClick = {
                    scope.launch {
                        openUrl(
                            snackbarHostState = snackbarHostState,
                            context = context,
                            url = news.url,
                            message = "News not available"
                        )
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.book),
                    contentDescription = "News",
                    modifier = Modifier.size(35.dp),
                )
            }
            SingleLineText(
                text = "Read full text",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailsScreenPreview() {
    val newsItem = News(
        id = 30429,
        title = "Framonauts Splash Down Near California",
        author = "Marcia Smith",
        image = Image(
            name = "Dragon Recovery",
            url = "https://spacepolicyonline.com/wp-content/uploads/2025/03/dragon-recovery-locations-CA-300x143.png"
        ),
        publishedAt = "2025-04-05T03:32:23Z",
        url = "https://spacepolicyonline.com/news/framonauts-splash-down-near-california/",
        summary = "The four crew members of Fram2, a private astronaut mission that circled the North and South Poles for the first time, is back home. Calling themselves “Framonauts,” they spent three-and-a-half […]"
    )

    NewsDetailsScreen(
        news = newsItem,
        onBackClick = {}
    )
}
