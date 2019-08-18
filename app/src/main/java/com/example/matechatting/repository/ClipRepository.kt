package com.example.matechatting.repository

import com.example.matechatting.network.IdeaApi
import com.example.matechatting.network.PostImageService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class ClipRepository :BaseRepository{

    fun postImage(file: MultipartBody.Part){
        IdeaApi.getApiService(PostImageService::class.java).postImage(file)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {}
            .subscribe()
    }
}