package com.dts.retrofit.data.impl

import android.content.Context
import androidx.room.Room
import com.dts.retrofit.data.Item
import com.dts.retrofit.data.dao.NewsDatabase
import com.dts.retrofit.data.db.NewsAppDb
import com.dts.retrofit.data.entity.NewsEntity
import com.dts.retrofit.domain.OnLoadNewsCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDatabaseImpl(context: Context): NewsDatabase {

    private val db = Room.databaseBuilder(
        context,
        NewsAppDb::class.java,
        NewsAppDb.NAME
    ).build()

    private val dao = db.NewsDao()

    override suspend fun save(news: NewsEntity)= dao.crete(news)

    override suspend fun updateOne(news: NewsEntity) = dao.update(news)

    override suspend fun delete(news: NewsEntity) = dao.delete(news)

    override suspend fun fetchNews(): List<Item> {
        return dao.all().map { Item.create(it) }
    }
}
