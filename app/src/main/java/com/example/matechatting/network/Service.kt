package com.example.matechatting.network

import com.example.matechatting.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("user/log_in")
    fun getLoginAndGetToken(@Field("stu_id") stuId: String, @Field("password") password: String): Observable<LoginBean>
}