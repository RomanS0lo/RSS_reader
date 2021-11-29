package com.dts.retrofit.domain

import com.dts.retrofit.model.NewsModel

interface OnLoadNewsCallback {

    fun onLoad(items: List<NewsModel>)

    fun onFail(message: String)
}
