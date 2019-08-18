package com.example.matechatting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matechatting.repository.HomeItemRepository

class HomeItemViewModelFactory(private val repository: HomeItemRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeItemViewModel(repository) as T
    }
}