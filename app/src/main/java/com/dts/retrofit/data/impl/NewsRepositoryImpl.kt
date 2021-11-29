package com.dts.retrofit.data.impl

import android.content.Context
import android.net.ConnectivityManager
import com.dts.retrofit.NewsApp
import com.dts.retrofit.data.NewsRepository
import com.dts.retrofit.domain.ApiClient
import com.dts.retrofit.domain.OnLoadNewsCallback
import com.dts.retrofit.model.NewsModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class NewsRepositoryImpl(private val context: Context) : NewsRepository, CoroutineScope {

    private val apiClient = ApiClient()
    private val job = Job()

    override fun fetchNews(callback: OnLoadNewsCallback, sourceUrl: String, pathUrl: String) {
        launch {
            val items = if (hasInternet()) {
                apiClient.loadNews(sourceUrl, pathUrl)
            } else {
                NewsApp.db.fetchNews()
            }

            val resultItems = items.map {
                NewsModel(
                    it.title.orEmpty(),
                    Date(it.pubDate),
                    it.author.orEmpty(),
                    it
                )
            }

            withContext(Dispatchers.Main) {
                callback.onLoad(resultItems)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO.plus(job)

    private fun hasInternet(): Boolean {
        val networkManager = context.getSystemService(ConnectivityManager::class.java)
        return networkManager?.activeNetwork != null && networkManager.activeNetworkInfo?.isConnected == true
    }
}
