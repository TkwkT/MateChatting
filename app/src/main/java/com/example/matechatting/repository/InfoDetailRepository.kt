package com.example.matechatting.repository

import android.util.Log
import com.example.matechatting.bean.UserBean
import com.example.matechatting.network.GetUserById
import com.example.matechatting.network.IdeaApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class InfoDetailRepository : BaseRepository {

    fun getDetail(id: Int, callback: (UserBean) -> Unit) {
        IdeaApi.getApiService(GetUserById::class.java, false).getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                callback(setInfo(it))
                Log.d("aaa", "getDetail$it")
            }
            .subscribe()
    }

    private fun setInfo(userBean: UserBean):UserBean {
        userBean.apply {
            if (!dire.isNullOrEmpty()) {
                val sb = StringBuilder()
                sb.append(dire[0] + " ")
                sb.append(dire[1] + " ")
                sb.append(dire[2])
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