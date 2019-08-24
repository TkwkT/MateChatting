package com.example.matechatting.function.mine

import android.util.Log
import com.example.matechatting.MyApplication
import com.example.matechatting.network.GetMineService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.base.BaseRepository
import com.example.matechatting.bean.MineBean
import com.example.matechatting.bean.UserBean
import com.example.matechatting.database.LoginDao
import com.example.matechatting.database.UserInfoDao
import com.example.matechatting.otherprocess.repository.AccountRepository
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected
import com.example.matechatting.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MineRepository(private val userInfoDao: UserInfoDao) : BaseRepository {

    fun getMine(callback: (UserBean) -> Unit) {
        if (isNetworkConnected(MyApplication.getContext()) == NetworkState.NONE) {
            getMineFromDB(callback)
        } else {
            getMineFromNet(callback)
        }
    }

    private fun getMineFromNet(callback: (UserBean) -> Unit) {
        IdeaApi.getApiService(GetMineService::class.java).getMine()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                val info = setInfo(it)
                callback(info)
                saveInDB(info)
            }
            .subscribe()
    }

    private fun saveInDB(userBean: UserBean) {
        runOnNewThread {
            userInfoDao.insertUserInfo(userBean)
        }
    }

    private fun getMineFromDB(callback: (UserBean) -> Unit) {
        MyApplication.getUserId()?.let {
            userInfoDao.getUserInfo(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(callback)
                .subscribe()
        }
    }

    private fun setInfo(userBean: UserBean): UserBean {
        userBean.apply {
            if (!directions.isNullOrEmpty()) {
                val sb = StringBuilder()
                for (s: String in directions!!) {
                    sb.append(" ")
                    sb.append(s)
                }
                direction = sb.toString()
            }
            val sb = StringBuilder()
            sb.append(graduationYear)
            sb.append("级毕业")
            graduation = sb.toString()
        }
        return userBean
    }

    companion object{
        @Volatile
        private var instance: MineRepository? = null

        fun getInstance(userInfoDao: UserInfoDao) =
            instance ?: synchronized(this) {
                instance ?: MineRepository(userInfoDao).also { instance = it }
            }
    }
}