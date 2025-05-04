package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto

data class NewsApiResponse(
    val results: List<NewsDto>
)

data class NewsDto(
    val id: Int,
    val title: String,
    val image_url: String,

    val published_at: String,
    val url: String,
    val summary: String,
)