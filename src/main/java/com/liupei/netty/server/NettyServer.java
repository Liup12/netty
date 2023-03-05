package com.liupei.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author : xuande
 * @date : 2023-03-04 17:36
 **/
public class NettyServer {

    public static void main(String[] args) {
        NettyServer.bind(8080);
    }


    public static void bind(int port){
        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(parent)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST ,true)
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)// 设置UDP读缓冲区为2M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)// 设置UDP写缓冲区为1M
                    .handler(new MyChannelInitializer());
            ChannelFuture sync = bootstrap.bind(port).sync();
            System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            parent.shutdownGracefully();
            child.shutdownGracefully();
        }

    }
}
