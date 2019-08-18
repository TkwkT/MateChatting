package com.example.matechatting.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ResetPassInterceptor(private val token:String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (token.isEmpty()) {
            val originalRequest = chain.request()
            chain.proceed(originalRequest)
        } else {
            Log.d("aaa", "ResetPassInterceptor : $token")
            val originalRequest = chain.request()
            val updateRequest = originalRequest.newBuilder().header("token", token).build()
            chain.proceed(updateRequest)
        }
    }

}