package com.example.matechatting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matechatting.repository.ChangePasswordByTokenRepository

class ChangePasswordByTokenViewModelFactory(private val repository: ChangePasswordByTokenRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChangePasswordByTokenViewModel(repository) as T
    }
}