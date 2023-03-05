package com.liupei.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;

/**
 * @author : xuande
 * @date : 2023-03-05 22:15
 **/
public class MyChannelInitializer extends ChannelInitializer<DatagramChannel> {



    @Override
    protected void initChannel(DatagramChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MyChannelHandler());
    }
}
