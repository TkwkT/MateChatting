package com.example.matechatting.function.infodetail

import android.util.Log
import com.example.matechatting.bean.UserBean
import com.example.matechatting.network.GetUserByIdService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.base.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class InfoDetailRepository : BaseRepository {

    fun getDetail(id: Int, callback: (UserBean) -> Unit) {
        IdeaApi.getApiService(GetUserByIdService::class.java, false).getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                callback(setInfo(it))
            }
            .subscribe()
    }

    private fun setInfo(userBean: UserBean):UserBean {
        userBean.apply {
            if (!dire.isNullOrEmpty()) {
                val sb = StringBuilder()
                sb.append(dire!![0] + " ")
                sb.append(dire!![1] + " ")
                sb.append(dire!![2])
                direction = sb.toString()
            }
            val sb = StringBuilder()
            sb.append(graduationYear)
            sb.append("级毕业")
            graduation = sb.toString()
        }
        return userBean
    }
}