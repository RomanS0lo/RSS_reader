package com.dts.retrofit.data.dao

import com.dts.retrofit.data.Item
import com.dts.retrofit.data.entity.NewsEntity

interface NewsDatabase {

    suspend fun save(news: NewsEntity)

    suspend fun updateOne(news: NewsEntity)

    suspend fun delete(news: NewsEntity)

    suspend fun fetchNews(): List<Item>
}
