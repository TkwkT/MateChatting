package com.example.matechatting.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountBean(
    @PrimaryKey
    val account:String,
    val password:String
)