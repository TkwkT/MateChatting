package com.example.matechatting.bean
import com.google.gson.annotations.SerializedName


data class LoginBean(
    @SerializedName("payload")
    var payload: String,
    @SerializedName("success")
    var success: Boolean
)