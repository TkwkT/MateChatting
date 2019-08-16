package com.example.matechatting.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.matechatting.bean.AccountBean
import io.reactivex.Single

@Dao
interface LoginDao{

    @Query("SELECT * FROM account WHERE account = :account")
    fun checkAccount(account:String): Single<AccountBean>

    @Query("SELECT token FROM account WHERE account = :account")
    fun getToken(account: String):Single<String>

    @Insert
    fun insertAccount(account:AccountBean)

    @Delete
    fun deleteAccount(account: AccountBean)

    @Query("DELETE FROM account")
    fun deleteAll()

    @Query("UPDATE account SET password = :password WHERE account = :account")
    fun updateAccount(account: String, password:String)
}