package com.example.matechatting

import android.app.Application
import android.content.Context
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import io.reactivex.plugins.RxJavaPlugins


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return
//        }
        RxJavaPlugins.setErrorHandler {
            val message = it.message?:""
            Log.d("aaa",message)
        }
//        sRefWatcher = LeakCanary.install(this)
        context = applicationContext
    }

    companion object {
        private lateinit var context: Context

        private var sRefWatcher: RefWatcher? = null

        fun getRefWatcher(): RefWatcher {
            return sRefWatcher!!
        }

        fun getContext(): Context {
            return context
        }
    }

}