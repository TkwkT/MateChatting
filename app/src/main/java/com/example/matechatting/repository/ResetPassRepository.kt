package com.example.matechatting.repository

import com.example.matechatting.network.ChangeResetPassService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.utils.ResetPassInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResetPassRepository : BaseRepository {

    fun changePass(password: String, interceptor: ResetPassInterceptor,callback: (Boolean) -> Unit) {
        IdeaApi.getApiService(ChangeResetPassService::class.java,needToken = false,tokenInterceptor = interceptor).changePassword(password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                callback(it.success)
            }
            .subscribe()
    }
}