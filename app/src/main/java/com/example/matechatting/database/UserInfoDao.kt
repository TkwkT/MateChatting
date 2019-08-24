package com.example.matechatting.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matechatting.bean.UserBean
import io.reactivex.Single

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userBean: UserBean)

    @Query("SELECT * FROM user_info WHERE id = :id")
    fun getUserInfo(id:Int): Single<UserBean>


}