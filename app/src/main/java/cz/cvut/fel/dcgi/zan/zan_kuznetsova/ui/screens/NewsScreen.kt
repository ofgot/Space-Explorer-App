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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.db.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.MoreLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem

@Composable
fun NewsScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    news: List<News>,
    onDetailsClick: (String) -> Unit
) {

    Scaffold(
        topBar = { NewsAppBar() },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NewsContent(
            news = news,
            modifier =  Modifier.padding(innerPadding),
            onDetailsClick = onDetailsClick,
        )
    }
}


@Composable
fun NewsContent(
    news: List<News>,
    onDetailsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(news) { singleNews ->
            NewsItem(
                news = singleNews,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onDetailsClick = {
                    onDetailsClick(singleNews.id.toString())
                }
            )
        }
    }
}

@Composable
fun NewsItem(
    news: News,
    modifier: Modifier,
    onDetailsClick: () -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        AsyncImage(
            model = news.image?.url,
            contentDescription = news.image?.name,
            modifier = Modifier
                .width(100.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ){
            MoreLineText(
                text = news.publishedAt,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 10.dp).fillMaxWidth(),
                maxLine = 1,
                textAlign = TextAlign.Right
            )

            MoreLineText(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                maxLine = 2,
                textAlign = TextAlign.Center,
            )

            Row (
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                IconButton(
                    onClick = onDetailsClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.book),
                        contentDescription = "See details",
                        modifier = Modifier.size(35.dp),

                    )
                }

                SingleLineText(
                    text = "Read article",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 2.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Show(){
    val fakeNews = News(
        id = 1,
        title = "NASA Successfully Tests New Mars Lander Engines and want money and want money and want money and want money",
        author = "Eric Berger",
        image = Image(
            name = "Mars Lander",
            url = "https://thespacedevs-prod.nyc3.digitaloceanspaces.com/media/images/spectrum_on_the_image_20250321072643.jpeg"
        ),
        publishedAt = "2025-04-01T15:30:00Z",
        url = "https://space-news.com/nasa-mars-lander-engine-test",
        summary = "NASA has successfully tested its next-generation Mars lander engines, preparing for future manned missions to the Red Planet."
    )

    NewsItem(
        news = fakeNews,
        modifier = Modifier
            .padding(16.dp),
        onDetailsClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar() {
    TopAppBar(
        title = {
            Text(text = "News")
        },
    )
}
