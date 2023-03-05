package com.liupei.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author : xuande
 * @date : 2023-03-04 19:03
 **/
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 8080);
    }

    public void connect(String host, int port){

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();


        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());

            Channel ch = bootstrap.bind(8081).sync().channel();
            for (int i = 0; i < 2; i++) {
                ch.writeAndFlush(new DatagramPacket(
                        Unpooled.copiedBuffer("你好端口7397的bugstack虫洞栈，我是客户端小爱，你在吗！" + i, Charset.forName("GBK")),
                        new InetSocketAddress("127.0.0.1", 8080))).sync();
            }
            //向目标端口发送信息

            ch.closeFuture().await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
