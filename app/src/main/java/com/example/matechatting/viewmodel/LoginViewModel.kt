package com.example.matechatting.viewmodel

import androidx.lifecycle.ViewModel
import com.example.matechatting.MyApplication
import com.example.matechatting.repository.LoginRepository
import com.example.matechatting.repository.LoginState
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun checkAccount(account: String?, password: String?, callback: (state: Int) -> Unit) {
        if (account.isNullOrEmpty()) {
            callback(LoginState.ACCOUNT_NULL)
        }
        if (password.isNullOrEmpty()) {
            callback(LoginState.PASSWORD_NULL)
        }
        val state = isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE) {
            repository.checkFromDatabase(account!!, password!!, callback)
        } else {
            repository.checkFromNetwork(account!!, password!!, callback)
        }
    }
}

