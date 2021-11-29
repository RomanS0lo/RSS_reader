package com.dts.retrofit.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dts.retrofit.data.dao.NewsDao
import com.dts.retrofit.data.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    version = 1
)
abstract class NewsAppDb : RoomDatabase() {

    companion object {
        const val NAME = "newsDatabase"
    }

    abstract fun NewsDao(): NewsDao
}
