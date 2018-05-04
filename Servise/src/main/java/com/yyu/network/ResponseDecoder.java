package com.yyu.network;

import com.yyu.Util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ResponseDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public ResponseDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
//        int dataLength = in.readInt();
        int dataLength = in.readableBytes();
        if (dataLength < 0) {
            ctx.close();
        }
//        if (in.readableBytes() < dataLength) {
//            in.resetReaderIndex();
//            return;
//        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = SerializationUtil.deserialize(data, genericClass);
        out.add(obj);
    }
}
