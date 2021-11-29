package com.dts.retrofit.domain.ua_news


import com.dts.retrofit.data.RssMainData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UPApiService {

    @GET("{rss_path}")
    fun getNews(@Path("rss_path", encoded = true) path: String): Call<RssMainData>
}
