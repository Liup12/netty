package com.liupei.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : xuande
 * @date : 2023-03-04 22:23
 **/
public class MyEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        String message = o.toString();
        byte[] bytes = message.getBytes();
        byte[] send = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, send , 1, bytes.length);
        send[0] = 0x02;
        send[send.length - 1] = 0x03;
        byteBuf.writeInt(send.length);
        byteBuf.writeBytes(send);
    }
}
