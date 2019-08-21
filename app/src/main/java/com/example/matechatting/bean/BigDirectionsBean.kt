package com.example.matechatting.bean
import com.google.gson.annotations.SerializedName


data class BigDirectionsBean(
    @SerializedName("direction_name")
    var directionName: String,
    @SerializedName("id")
    var id: Int
)