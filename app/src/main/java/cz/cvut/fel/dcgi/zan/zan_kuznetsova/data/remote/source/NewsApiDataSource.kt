package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.source

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.api.NewsLibraryApi

class NewsApiDataSource(
    private val api: NewsLibraryApi
) {

    suspend fun getNews(): List<News> {
        val news = api.getNews().results

        return news.map { dto ->
            News(
                id = dto.id,
                title = dto.title,
                image = dto.image_url,

                publishedAt = dto.published_at,
                url = dto.url,
                summary = dto.summary
            )
        }
    }
}