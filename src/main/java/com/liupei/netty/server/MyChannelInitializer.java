package com.liupei.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author : xuande
 * @date : 2023-03-05 22:19
 **/
public class MyChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    EventLoopGroup group = new NioEventLoopGroup();


    @Override
    protected void initChannel(NioDatagramChannel channel) throws Exception {
        channel.pipeline().addLast(group, new MyChannelHandler());
    }
}
