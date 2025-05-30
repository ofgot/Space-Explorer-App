package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedAt ASC")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news  WHERE id = :id")
    suspend fun getNewsForId(id: Int): NewsEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()

    @Query("UPDATE news SET comment = :comment WHERE id = :id")
    suspend fun updateComment(id: Int, comment: String)

    @Query("SELECT EXISTS(SELECT 1 FROM news)")
    suspend fun hasAnyNews(): Boolean

    @Query("SELECT id FROM news")
    suspend fun getAllIds(): List<Int>

    @Query("DELETE FROM news WHERE id IN (:ids)")
    suspend fun deleteNewsByIds(ids: List<Int>)
}
