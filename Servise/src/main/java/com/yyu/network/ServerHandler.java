package com.yyu.network;

import com.yyu.POJO.IOBody;
import com.yyu.Util.SerializationUtil;
import com.yyu.execute.Execute;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        System.out.printf("serverhandler");
        System.out.println(msg);
        String body = (String) msg;
        Object result;
        IOBody IOBody = new IOBody();

        result = this.execute(body);
        IOBody.setResult(result);
        byte[] resultBytes = SerializationUtil.serialize(IOBody);
        byte[] lineBytes = System.getProperty("line.separator").getBytes();

        byte[] resultData = this.byteArrayMerge(resultBytes, lineBytes);

        ByteBuf resp = Unpooled.copiedBuffer(resultData);
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }

    private HashMap<String, String> execute(String cmd) throws Exception{
        return new Execute().exeProcess(cmd);
    }

    private byte[] byteArrayMerge(byte[] byteArray1, byte[] byteArray2){
        byte[] resultData = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, resultData, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, resultData, byteArray1.length, byteArray2.length);
        return resultData;
    }
}
