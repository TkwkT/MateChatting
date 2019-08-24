package com.example.matechatting.function.myinfo

import com.example.matechatting.MyApplication
import com.example.matechatting.base.BaseRepository
import com.example.matechatting.bean.PostUserBean
import com.example.matechatting.bean.UserBean
import com.example.matechatting.database.LoginDao
import com.example.matechatting.database.UserInfoDao
import com.example.matechatting.network.GetMyInfoService
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.network.UpdateUserInfoService
import com.example.matechatting.otherprocess.repository.AccountRepository
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected
import com.example.matechatting.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyInfoRepository(private val userInfoDao: UserInfoDao) : BaseRepository {

    fun saveMyInfo(userBean: UserBean) {
        saveInDB(userBean)
        saveInNet(userBean)
    }

    private fun saveInDB(userBean: UserBean) {
        runOnNewThread {
            userInfoDao.insertUserInfo(userBean)
        }
    }

    private fun saveInNet(userBean: UserBean) {
        IdeaApi.getApiService(UpdateUserInfoService::class.java).update(userBeanToPostUserBean(userBean))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
            }
            .subscribe()
    }

    private fun userBeanToPostUserBean(userBean: UserBean): PostUserBean {
        val postUserBean = PostUserBean()
        userBean.apply {
            postUserBean.city = city
            postUserBean.company = company
            postUserBean.email = email
            postUserBean.gender = gender
            postUserBean.graduationYear = graduationYear
            postUserBean.job = job
            postUserBean.majorName = majorName
            postUserBean.qqAccount = qqAccount
            postUserBean.slogan = slogan
            postUserBean.wechatAccount = wechatAccount
        }
        return postUserBean
    }


    fun getMyInfo(callback: (UserBean) -> Unit) {
        if (isNetworkConnected(MyApplication.getContext()) == NetworkState.NONE) {
            getMyInfoFromDB(callback)
        } else {
            getMyInfoFromNet(callback)
        }
    }

    private fun getMyInfoFromNet(callback: (UserBean) -> Unit) {
        IdeaApi.getApiService(GetMyInfoService::class.java).getMyInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                val info = setInfo(it)
                callback(info)
                saveInDB(info)
            }
            .subscribe()
    }

    private fun getMyInfoFromDB(callback: (UserBean) -> Unit) {
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
        private var instance: MyInfoRepository? = null

        fun getInstance(userInfoDao: UserInfoDao) =
            instance ?: synchronized(this) {
                instance ?: MyInfoRepository(userInfoDao).also { instance = it }
            }
    }
}