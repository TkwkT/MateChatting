package com.example.matechatting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matechatting.repository.ForgetPasswordRepository

class ForgetPasswordViewModelFactory(private val repository: ForgetPasswordRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForgetPasswordViewModel(repository) as T
    }
}