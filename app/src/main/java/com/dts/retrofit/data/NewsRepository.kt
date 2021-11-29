package com.dts.retrofit.data

import com.dts.retrofit.domain.OnLoadNewsCallback

interface NewsRepository {

     fun fetchNews(callback: OnLoadNewsCallback, sourceUrl: String, pathUrl: String)
}
