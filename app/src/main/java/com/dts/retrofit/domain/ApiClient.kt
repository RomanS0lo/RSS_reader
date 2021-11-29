package com.dts.retrofit.domain

import com.dts.retrofit.NewsApp
import com.dts.retrofit.data.Item
import com.dts.retrofit.data.UkrChannel
import com.dts.retrofit.data.entity.NewsEntity
import com.dts.retrofit.domain.ua_news.UPApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ApiClient : CoroutineScope {


    //https://www.pravda.com.ua/rss/view_pubs/
    //https://www.seattletimes.com/health/feed/


    suspend fun loadNews(source: String, path: String): List<Item> {
        val retrofit = createRetrofit(source)
        val api = retrofit.create(UPApiService::class.java)
        val call = api.getNews(path)
        val response = call.execute()
        return if (response.isSuccessful) {
            val channel = response.body()?.channel
            Timber.i("UA channel: ${channel?.javaClass} -> ${(channel as? UkrChannel)?.image?.title}")
            if (channel != null) {
                val items = channel.items.orEmpty()
                items.forEach { NewsApp.db.save(NewsEntity.create(it)) }
                items
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    private fun createRetrofit(source: String): Retrofit {
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
        return Retrofit.Builder()
            .baseUrl(source)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(okhttpClient)
            .build()
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)
}
