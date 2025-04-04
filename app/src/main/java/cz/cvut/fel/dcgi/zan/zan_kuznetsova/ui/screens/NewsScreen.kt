package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.R
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.BottomNavigation
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
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = news.image?.url,
            contentDescription = news.image?.name,
            modifier = Modifier
                .width(100.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SingleLineText(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            SingleLineText(
                text = news.author,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            SingleLineText(
                text = news.publishedAt,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            Row {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar() {
    TopAppBar(
        title = {
            Text(text = "News")
        },
    )
}
