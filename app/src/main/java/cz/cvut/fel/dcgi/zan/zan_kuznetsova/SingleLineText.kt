package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

object SingleLineText {
    @Composable
    fun SingleLineText(
        text: String,
        style: TextStyle,
        modifier: Modifier = Modifier,
    ) {
        Text(
            text = text,
            style = style,
            modifier = modifier,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}