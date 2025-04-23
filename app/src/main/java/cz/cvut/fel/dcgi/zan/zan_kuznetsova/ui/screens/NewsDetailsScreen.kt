package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
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
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.MoreLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.SingleLineText
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components.openUrl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.viewmodel.events.NewsEvent
import kotlinx.coroutines.launch
import org.w3c.dom.Comment

@Composable
fun NewsDetailsScreen(
    news: News,
    onBackClick: () -> Unit,

    // Comment
    comment: String,
    isEditing: Boolean,
    onClick: (NewsEvent) -> Unit
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
            snackbarHostState = snackbarHostState,

            comment = comment,
            isEditing = isEditing,
            onClick = onClick,
        )
    }
}


@Composable
fun NewsDetailsContent(
    news: News,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,

    // Comment
    comment: String,
    isEditing: Boolean,
    onClick: (NewsEvent) -> Unit
) {
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            NewsDetailsItem(
                news,
                comment = comment,
                modifier = Modifier.padding(top = 4.dp),

                snackbarHostState = snackbarHostState,
                isEditing = isEditing,
                onClick = onClick,
            )
        }
    }
}

@Composable
fun NewsDetailsItem(
    news: News,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,

    // Comment
    comment: String,
    isEditing: Boolean,

    onClick: (NewsEvent) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            maxLine = Int.MAX_VALUE,
            textAlign = TextAlign.Center,
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
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

    HorizontalDivider(thickness = 2.dp)

    SingleLineText(
        text = "Comment",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    )

    if (isEditing) {
        OutlinedTextField(
            value = comment,
            onValueChange = { newComment ->
                onClick(NewsEvent.OnCommentChange(newComment))
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onClick(NewsEvent.OnSaveComment)
                onClick(NewsEvent.OnToggleEditing)
            }
        ) {
            Text("Save comment")
        }
    } else {
        Text(
            text = comment.ifBlank { "No comment yet. Tap to add one." },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(NewsEvent.OnToggleEditing) },
        )
    }
}
