package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launches ORDER BY net ASC")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Query("SELECT * FROM launches  WHERE id = :id")
    suspend fun getLaunchForId(id: String): LaunchEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("DELETE FROM launches")
    suspend fun deleteAllLaunches()
}
