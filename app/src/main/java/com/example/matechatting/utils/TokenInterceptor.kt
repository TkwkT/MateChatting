package com.example.matechatting.utils

import okhttp3.Interceptor
import okhttp3.Response

//class TokenInterceptor :Interceptor{
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = MainDataManager.getInstance().getToken()
//        if (StringUtils.isEmpty(token)) {
//            val originalRequest = chain.request()
//            return chain.proceed(originalRequest)
//        } else {
//            val originalRequest = chain.request()
//            val updateRequest = originalRequest.newBuilder().header("access_token", token).build()
//            return chain.proceed(updateRequest)
//        }
//    }
//
//}