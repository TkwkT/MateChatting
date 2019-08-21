package com.example.matechatting.otherprocess.netty

import android.util.Log
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

@ChannelHandler.Sharable
class ClientHandler : ChannelInboundHandlerAdapter() {
    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush("hello server!")
    }

    @Throws(Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val s = msg as String
        Log.d("ccc", "ClientHandler channelRead:$s")
    }
}
