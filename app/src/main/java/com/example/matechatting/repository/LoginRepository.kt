package com.example.matechatting.repository

import android.util.Log
import com.example.matechatting.MyApplication
import com.example.matechatting.bean.AccountBean
import com.example.matechatting.database.LoginDao
import com.example.matechatting.network.IdeaApi
import com.example.matechatting.network.LoginService
import com.example.matechatting.repository.LoginState.Companion.NO_NETWORK
import com.example.matechatting.repository.LoginState.Companion.OK
import com.example.matechatting.repository.LoginState.Companion.ERROR
import com.example.matechatting.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class LoginRepository(private val accountDao: LoginDao) : BaseRepository {

    fun checkFromDatabase(account: String, password: String, callback: (state: Int) -> Unit) {
        accountDao.checkAccount(account)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (password == it.password) {
                    saveState(account, it.token)
                    callback(OK)
                } else if (password != it.password) {
                    callback(ERROR)
                }
            }, {
                callback(NO_NETWORK)
            })
    }

    fun checkFromNetwork(account: String, password: String, callback: (state: Int) -> Unit) {
        IdeaApi.getApiService(LoginService::class.java, false).getLoginAndGetToken(account, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val token = it.payload
                Log.d("aaa", token)
                callback(OK)
                saveState(account, token)
                saveInDB(account, password, token)
            },{
                callback(ERROR)
            })
    }

    private fun saveState(account: String, token: String) {
        MyApplication.saveLoginState(account, token)
    }

    private fun saveInDB(account: String, password: String, token: String) {
        val accountBean = AccountBean(account, password, token)
        runOnNewThread {
            accountDao.insertAccount(accountBean)
        }
    }

    companion object {

        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(classifyDao: LoginDao) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(classifyDao).also { instance = it }
            }
    }

}

class LoginState {
    companion object {
        //验证成功
        const val OK = 0
        //输入账号为空
        const val ACCOUNT_NULL = 1
        //输入密码为空
        const val PASSWORD_NULL = 2
        //账号或密码错误
        const val ERROR = 3
        //请连接网络
        const val NO_NETWORK = 4
    }
}