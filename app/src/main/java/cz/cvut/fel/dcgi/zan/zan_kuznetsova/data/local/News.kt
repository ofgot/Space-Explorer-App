package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local

data class News(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",

    val publishedAt: String = "",
    val url: String = "",
    val summary: String = "",

    // Comment
    val comment: String = ""
)
