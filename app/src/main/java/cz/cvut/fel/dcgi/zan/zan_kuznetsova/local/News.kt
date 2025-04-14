package cz.cvut.fel.dcgi.zan.zan_kuznetsova.local

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.db.Image

data class News(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val image: Image?,
    val publishedAt: String = "",
    val url: String = "",
    val summary: String = "",
)
