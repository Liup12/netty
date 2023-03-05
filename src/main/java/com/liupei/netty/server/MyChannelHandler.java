package com.liupei.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : xuande
 * @date : 2023-03-05 22:19
 **/
public class MyChannelHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString();
        byte [] bytes = new byte[packet.content().readableBytes()];
        packet.content().readBytes(bytes);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " UDP服务端接收到消息：" + new String(bytes, "GBK"));


        String response = "服务端已经接收到了你的消息";

        byte[] gbks = response.getBytes(Charset.forName("GBK"));
        DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer(gbks), packet.sender());
        channelHandlerContext.writeAndFlush(datagramPacket);
    }
}
