package com.example.matechatting.otherprocess.inout

import android.util.Log
import com.example.matechatting.otherprocess.bean.Message
import com.example.matechatting.otherprocess.netty.NettyClient

object MessageWrite {
    fun writeLoginMessage(token: String) {
//        val msg = Message(LOG_IN, 0, 0, token, "")
//        Log.d("aaa", "Client.channel" + Client.channel.toString())
//        NettyClient.channel?.writeAndFlush(token)
    }

    private fun write(message: Message) {
//        Client.channel?.writeAndFlush(message)
    }
}