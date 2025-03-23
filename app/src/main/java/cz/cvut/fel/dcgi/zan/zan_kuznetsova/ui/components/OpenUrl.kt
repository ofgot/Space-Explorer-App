package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

suspend fun openUrl(
    snackbarHostState: SnackbarHostState,
    context: Context,
    url: String?,
    message: String = "Unable to open the link"
) {
    if (!url.isNullOrBlank()) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
        }
    } else {
        snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
    }
}