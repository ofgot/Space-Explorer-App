package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao.NewsDao
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.NewsEntity
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsDBDataSourceImpl(
    private val newsDao: NewsDao
) : DBDataSource<News, Int> {

    override fun getAll(): Flow<List<News>> =
        newsDao.getAllNews().map { entities ->
            entities.map { it.toNews() }
        }

    override suspend fun getById(id: Int): News =
        newsDao.getNewsForId(id).toNews()

    override suspend fun insertAll(data: List<News>) =
        newsDao.insertNews(
            data.map {
                it.toNewsEntity()
            }
        )

    override suspend fun deleteAll() =
        newsDao.deleteAllNews()

    override suspend fun updateComment(id: Int, comment: String) {
        newsDao.updateComment(id, comment)
    }

    override suspend fun hasAnyNews(): Boolean = newsDao.hasAnyNews()
}

// Mapping

fun News.toNewsEntity(): NewsEntity = NewsEntity(
    id = id,
    title = title,
    author = author,
    image = image,
    publishedAt = publishedAt,
    url = url,
    summary = summary,

    // Comment
    comment = comment,
)

fun NewsEntity.toNews(): News = News(
    id = id,
    title = title,
    author = author,
    image = image,
    publishedAt = publishedAt,
    url = url,
    summary = summary,

    // Comment
    comment = comment
)
