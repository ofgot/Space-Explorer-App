package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.DBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News

class NewsRepository (
    private val newsDBDataSource: DBDataSource<News, Int>
) {
    fun getAllNews() = newsDBDataSource.getAll()

    suspend fun insertNews(news: List<News>) =
        newsDBDataSource.insertAll(news)

    suspend fun deleteAllNews() =
        newsDBDataSource.deleteAll()

    suspend fun getNewsById(id: Int) =
        newsDBDataSource.getById(id)

    suspend fun updateComment(id: Int, comment: String) {
        newsDBDataSource.updateComment(id, comment)
    }

    suspend fun hasNews(): Boolean = newsDBDataSource.hasAnyNews()
}