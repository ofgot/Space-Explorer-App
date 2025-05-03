package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.Converters
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao.LaunchDao
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.LaunchEntity

@TypeConverters(Converters::class)
@Database(entities = [LaunchEntity::class], version = 10)
abstract class LaunchDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao

    companion object {
        @Volatile
        private var INSTANCE: LaunchDatabase? = null

        fun getDatabase(context: Context): LaunchDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    LaunchDatabase::class.java,
                    "launches_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
    }
}
