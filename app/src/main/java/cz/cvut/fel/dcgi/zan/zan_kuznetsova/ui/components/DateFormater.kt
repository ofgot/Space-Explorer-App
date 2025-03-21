package cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.components

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatLaunchDate(isoDate: String): String {
    return try {
        val parsedDate = ZonedDateTime.parse(isoDate)
            .withZoneSameInstant(ZoneId.systemDefault())

        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm z", Locale.ENGLISH)
        parsedDate.format(formatter)
    } catch (e: Exception) {
        "Unknown Date"
    }
}