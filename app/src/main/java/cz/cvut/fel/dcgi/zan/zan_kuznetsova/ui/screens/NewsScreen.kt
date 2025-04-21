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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.temporary.sampleNews
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.MoreLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.BottomNavItem
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.Routes

@Composable
fun NewsScreen(
    mainBottomNavigationItems: List<BottomNavItem>,
    currentDestination: String?,
    query: String,
    news: List<News>,
    onQueryChange: (String) -> Unit,
    onDetailsClick: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            NewsAppBar()
        },
        bottomBar = { BottomNavigation(mainBottomNavigationItems, currentDestination) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NewsContent(
            news = news,
            modifier = Modifier.padding(innerPadding),
            onDetailsClick = onDetailsClick,
            query = query,
            onQueryChange = onQueryChange
        )
    }
}


@Composable
fun NewsContent(
    news: List<News>,
    onDetailsClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                label = { Text("Search news...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        items(news) { singleNews ->
            NewsItem(
                news = singleNews,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onDetailsClick = {
                    onDetailsClick(singleNews.id)
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
) {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            MoreLineText(
                text = news.publishedAt,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 10.dp)
                    .fillMaxWidth(),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar() {
    TopAppBar(
        title = {
            SingleLineText(
                text = "News",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )
        },
    )
}


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {

    val mainBottomNavItem = remember {
        listOf(
            BottomNavItem(
                route = Routes.LaunchesGraph,
                label = "Launches",
                iconId = R.drawable.rocket,
                contentDescription = "Launches nav bar item",
                onClick = {
                }
            ),
            BottomNavItem(
                route = Routes.News,
                label = "News",
                iconId = R.drawable.news,
                contentDescription = "News nav bar item",
                onClick = {
                }
            ),
            BottomNavItem(
                route = Routes.Settings,
                label = "Settings",
                iconId = R.drawable.settings,
                contentDescription = "Settings nav bar item",
                onClick = {
                }
            )
        )
    }

    NewsScreen(
        mainBottomNavigationItems = mainBottomNavItem,
        currentDestination = "news_route",
        query = "spacex",
        news = sampleNews,
        onQueryChange = {},
        onDetailsClick = {}
    )
}


