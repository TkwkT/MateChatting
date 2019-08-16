package com.example.matechatting

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.edit
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

        fun getContext(): Context {
            return context
        }

        fun saveLoginState(account: String,token:String){
            context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE).edit {
                putBoolean("isLogin", true)
                putString("account", account)
                putString("token",token)
                commit()
            }
        }

        fun getToken():String?{
            val sharedPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE)
            return sharedPreferences.getString("token","")
        }
    }

}