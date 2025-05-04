package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao.NewsDao
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 8)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
    }
}