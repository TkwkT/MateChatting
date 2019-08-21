package com.example.matechatting.function.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
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