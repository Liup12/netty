package com.liupei.netty.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author : xuande
 * @date : 2023-03-04 19:49
 **/
public class MyDecoder extends ByteToMessageDecoder {


    private final int BASE_LENGTH = 4;


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        int size = in.readableBytes();
        byte[] bytes = new byte[size];
        in.readBytes(bytes);

        byte begin = bytes[0];
        byte end = bytes[bytes.length - 1];

        // 丢包
        if (begin != 0x02 && end == 0x03){
            channelHandlerContext.writeAndFlush("error");
            return;
        }

        // 半包
        if (begin == 0x02 && end != 0x03){
            // 重制读取点
            in.resetReaderIndex();
            return;
        }

        System.out.println(JSON.toJSONString(bytes));

        //越过02 03位
        ByteBuf copy = in.copy(1, size - 1);
        //转换
        String msg = copy.toString(Charset.forName("GBK"));
        //填充
        list.add(msg);

    }
}
