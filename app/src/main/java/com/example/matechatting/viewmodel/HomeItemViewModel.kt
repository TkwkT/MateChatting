package com.example.matechatting.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.matechatting.bean.HomeItemBean
import com.example.matechatting.repository.HomeItemRepository
import com.example.matechatting.utils.NetworkState
import com.example.matechatting.utils.isNetworkConnected

class HomeItemViewModel(private val repository: HomeItemRepository) :ViewModel(){

    fun getData(context:Context,callback:(LiveData<PagedList<HomeItemBean>>) -> Unit){
        if (isNetworkConnected(context) == NetworkState.NONE){

        }else{
            repository.getHomeItemFromNet(callback)
        }
    }
}