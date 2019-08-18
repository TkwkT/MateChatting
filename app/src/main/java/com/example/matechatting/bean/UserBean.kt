package com.example.matechatting.bean
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName


data class UserBean(
    @SerializedName("city")
    var city: String,
    @SerializedName("company")
    var company: String,
    @SerializedName("directions")
    @Ignore
    var dire: List<String>,
    @SerializedName("email")
    var email: String,
    @SerializedName("graduation_year")
    var graduationYear: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("job")
    var job: String,
    @SerializedName("major_name")
    var majorName: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: Long,
    @SerializedName("qq_account")
    var qqAccount: Int,
    @SerializedName("slogan")
    var slogan: String,
    @SerializedName("stu_id")
    var stuId: String,
    @SerializedName("wechat_account")
    var wechatAccount: String
){
    var graduation = ""
    var direction = ""
}