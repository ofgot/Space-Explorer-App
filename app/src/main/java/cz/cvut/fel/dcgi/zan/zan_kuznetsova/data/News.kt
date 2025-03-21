package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data

data class News(
    val id: Int,
    val title: String,
    val author: String,
    val image: Image,
    val publishedAt: String,
    val url: String,
    val summary: String,
)
