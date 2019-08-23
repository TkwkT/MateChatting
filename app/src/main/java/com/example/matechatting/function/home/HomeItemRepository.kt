package com.example.matechatting.function.home

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.matechatting.base.BaseRepository
import com.example.matechatting.bean.HomeItemBean
import com.example.matechatting.database.HomeItemDao

class HomeItemRepository(private val homeItemDao: HomeItemDao) : BaseRepository {

    fun getHomeItemFromDB(callback: (LiveData<PagedList<HomeItemBean>>) -> Unit) {
        val detailList = LivePagedListBuilder(
            homeItemDao.getHomeItem(), PagedList
                .Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(ENABLE_PLACEHOLDER).build()
        ).build()
        callback(detailList)
    }

    fun getHomeItemFromNet(callback: (LiveData<PagedList<HomeItemBean>>) -> Unit) {
        val homeItemList = LivePagedListBuilder(
            PagingHomeItemDataSourceFactory(), PagedList
                .Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(ENABLE_PLACEHOLDER).build()
        ).build()
        callback(homeItemList)
    }

    companion object {
        private const val PAGE_SIZE = 15
        private const val ENABLE_PLACEHOLDER = true

        @Volatile
        private var instance: HomeItemRepository? = null

        fun getInstance(homeItemDao: HomeItemDao) =
            instance ?: synchronized(this) {
                instance
                    ?: HomeItemRepository(homeItemDao).also { instance = it }
            }
    }
}