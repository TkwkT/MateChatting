package com.example.matechatting.network


object IdeaApi {

    fun <T> getApiService(cls: Class<T>,needToken: Boolean = true,baseUrl: String = "http://dcnb9k.natappfree.cc/"): T {
        val retrofit = RetrofitUtil.getRetrofitBuilder(baseUrl, needToken).build()
        return retrofit.create(cls)
    }
}