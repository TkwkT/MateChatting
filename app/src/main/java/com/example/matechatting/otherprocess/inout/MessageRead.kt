package com.example.matechatting.otherprocess.inout

import android.util.Log
import com.example.matechatting.otherprocess.bean.Message

object MessageRead {
    fun readMessage(message:Message){
        Log.d("ccc",message.payload.toString())
    }
}