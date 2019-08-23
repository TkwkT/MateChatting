package com.example.matechatting.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matechatting.bean.HomeItemBean

@Dao
interface HomeItemDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItems(homeItemBean: List<HomeItemBean>)

    @Query("SELECT * FROM home_item")
    fun getHomeItem(): DataSource.Factory<Int, HomeItemBean>
}