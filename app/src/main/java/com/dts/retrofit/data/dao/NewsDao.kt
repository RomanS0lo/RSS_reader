package com.dts.retrofit.data.dao

import androidx.room.*
import com.dts.retrofit.data.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun crete(news: NewsEntity)

    @Query("SELECT * FROM news_table WHERE guid == :guid")
    suspend fun read(guid: String): NewsEntity?

    @Delete
    suspend fun delete(news: NewsEntity)

    @Update
    suspend fun update(newsEntity: NewsEntity)

    @Query("SELECT * FROM news_table")
    suspend fun all(): List<NewsEntity>
}
