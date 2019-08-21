package com.example.matechatting.function.home

import androidx.paging.DataSource

class PagingHomeItemDataSourceFactory : DataSource.Factory<Int, HomeItemBean>() {
    override fun create(): DataSource<Int, HomeItemBean> {
        return PagingHomeItemDataSource()
    }
}