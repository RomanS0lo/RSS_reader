package com.dts.retrofit

import android.app.Application
import com.dts.retrofit.data.dao.NewsDatabase
import com.dts.retrofit.data.impl.NewsDatabaseImpl

class NewsApp : Application() {

    companion object {
        lateinit var db: NewsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = NewsDatabaseImpl(this)
    }
}
