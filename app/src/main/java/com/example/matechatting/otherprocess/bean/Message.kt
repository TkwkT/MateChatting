package com.example.matechatting.otherprocess.bean

import java.io.Serializable

data class Message(
    val subject: String,
    val send_user_id: Int,
    val accept_user_id: Int,
    val payload: Any,
    val time: String
) : Serializable

