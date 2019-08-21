package com.example.matechatting.function.mine

import android.util.Log
import com.example.matechatting.network.GetMineService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.base.BaseRepository
import com.example.matechatting.bean.MineBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MineRepository : BaseRepository {

    fun getMine(callback: (MineBean) -> Unit) {
        IdeaApi.getApiService(GetMineService::class.java).getMine()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.d("aaa", "getMine$it")
                callback(it)
            }
            .subscribe()
    }
}