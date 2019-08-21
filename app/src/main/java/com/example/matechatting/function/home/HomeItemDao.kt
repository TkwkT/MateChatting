package com.example.matechatting.function.home

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matechatting.function.home.HomeItemBean

@Dao
interface HomeItemDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItem(homeItemBean: HomeItemBean)

    @Query("SELECT * FROM home_item")
    fun getHomeItem(): DataSource.Factory<Int, HomeItemBean>
}