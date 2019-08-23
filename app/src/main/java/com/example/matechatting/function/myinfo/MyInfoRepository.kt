package com.example.matechatting.function.myinfo

import com.example.matechatting.MyApplication
import com.example.matechatting.base.BaseRepository
import com.example.matechatting.bean.UserBean
import com.example.matechatting.network.GetMyInfoService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class MyInfoRepository : BaseRepository {

    fun saveMyInfo(userBean: UserBean){
        if (isNetworkConnected(MyApplication.getContext()) != NetworkState.NONE){

        }
    }

    private fun saveInNet(userBean: UserBean){

    }

    private fun saveInDB(userBean: UserBean){

    }

    fun getMyInfo(callback: (UserBean) -> Unit) {
        IdeaApi.getApiService(GetMyInfoService::class.java).getMyInfo()
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