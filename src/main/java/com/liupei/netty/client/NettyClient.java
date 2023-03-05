package com.liupei.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new MyChannelInitializer());

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            System.out.println("itstack-demo-netty client start done.");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
