package com.liupei.netty.server;

import com.liupei.netty.codec.MyDecoder;
import com.liupei.netty.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @author : xuande
 * @date : 2023-03-04 17:37
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {


        channel.pipeline().addLast(new MyDecoder());
        channel.pipeline().addLast(new MyEncoder());
        channel.pipeline().addLast(new MyServerHandler());
    }
}
