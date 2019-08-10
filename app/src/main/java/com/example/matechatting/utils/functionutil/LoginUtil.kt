package com.example.matechatting.utils.functionutil

import android.util.Log
import com.example.matechatting.MyApplication
import com.example.matechatting.database.AppDatabase
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 未实现
 * 网络验证账号密码
 */
object LoginUtil {

    /**
     * 在LoginActivity调用检查用户账号密码
     * 连接网络时调用@link [checkFromNet]
     * 未连接网络时调用@link [checkFromDatabase]
     * @param account 用户输入账号
     * @param password 用户输入密码
     * @param callback 回调返回验证结果
     */
    fun checkAccount(account:String?,password:String?,callback:(state:Int,account:String) ->Unit){


        if (account.isNullOrEmpty()){
            callback(LoginState.ACCOUNT_NULL,"")
        }
        if (password.isNullOrEmpty()){
            callback(LoginState.PASSWORD_NULL,"")
        }
        val state = isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE){
            Log.d("aaa","eee")
            checkFromDatabase(account!!, password!!, callback)
        }else{
            checkFromNet(account!!, password!!, callback)
        }
    }

    private fun checkFromNet(account:String,password:String,callback:(state:Int,account:String) ->Unit){
        callback(LoginState.OK,account)
    }

    private fun checkFromDatabase(account:String,password:String,callback:(state:Int,account:String) ->Unit){
        AppDatabase.getInstance(MyApplication.getContext()).accountDao().checkAccount(account)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (password == it.password){
                    callback(LoginState.OK,account)
                }else if(password != it.password){
                    callback(LoginState.PASSWORD_ERROR,"")
                }
                Log.d("aaa","ccc")
            },{
                callback(LoginState.ACCOUNT_ERROR,"")
                Log.d("aaa","bbb")
            })
    }
}

class LoginState{
    companion object{
        //验证成功
        const val OK = 0
        //输入账号为空
        const val ACCOUNT_NULL = 1
        //输入密码为空
        const val PASSWORD_NULL = 2
        //密码错误
        const val PASSWORD_ERROR = 3
        //请连接网络
        const val ACCOUNT_ERROR = 4
        //账号不存在
        const val ACCOUNT_NO = 5
    }
}