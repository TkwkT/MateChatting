package com.example.matechatting.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matechatting.bean.HomeItemBean
import io.reactivex.Single

@Dao
interface HomeItemDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItems(homeItemBean: List<HomeItemBean>)

    @Query("SELECT * FROM home_item")
    fun getHomeItem(): DataSource.Factory<Int, HomeItemBean>

    @Query("SELECT * FROM home_item LIMIT :first,:first + 20")
    fun getHomeItemLimit(first:Int): Single<List<HomeItemBean>>
}