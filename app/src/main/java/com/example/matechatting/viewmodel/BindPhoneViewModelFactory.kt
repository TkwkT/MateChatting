package com.example.matechatting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matechatting.repository.BindPhoneRepository

class BindPhoneViewModelFactory(private val repository: BindPhoneRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BindPhoneViewModel(repository) as T
    }
}