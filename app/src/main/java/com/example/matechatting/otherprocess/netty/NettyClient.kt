package com.example.matechatting.otherprocess.netty

import com.example.matechatting.otherprocess.inout.MessageWrite
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

import java.net.InetSocketAddress

class NettyClient(private val host: String, private val port: Int) {

    fun start(token:String) {
        val group = NioEventLoopGroup()
        try {
            val bootstrap = Bootstrap()
            bootstrap.group(group)
                .channel(NioSocketChannel::class.java)
                .remoteAddress(host, port)
                .handler(object : ChannelInitializer<SocketChannel>() {
                    @Throws(Exception::class)
                    override fun initChannel(socketChannel: SocketChannel) {
                        socketChannel.pipeline().addLast(StringEncoder())
                            .addLast(StringDecoder())
                            .addLast(ClientHandler())
                    }
                })
            val future = bootstrap.connect().sync()
            channel = future.channel()
            MessageWrite.writeLoginMessage(token)
            future.channel().writeAndFlush("laksdjfoaiwerjoqiwejfowejifqwoeijfqwoeijroqwiejr")
            future.channel().closeFuture().sync()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object{
        var channel:Channel? = null
    }
}
