package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Image

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val title: String = "",
    val author: String = "",
    @Embedded(prefix = "image_") val image: Image? = null,
    val publishedAt: String = "",
    val url: String = "",
    val summary: String = "",

    // Comment
    val comment: String
)

