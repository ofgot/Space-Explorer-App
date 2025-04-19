package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.DBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.NewsDBDataSourceImpl
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.News

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

}