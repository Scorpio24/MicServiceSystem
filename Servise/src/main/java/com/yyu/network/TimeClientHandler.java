package com.yyu.network;

import com.yyu.POJO.Response;
import com.yyu.Util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.logging.Logger;

public class TimeClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;
    private byte[] req;
//    private final ByteBuf firstMessage;

    public TimeClientHandler(){
        req = ("python D:/test.py decs" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf mesaage = null;
        mesaage = Unpooled.buffer(req.length);
        mesaage.writeBytes(req);
        ctx.writeAndFlush(mesaage);
//        for (int i = 0; i < 100; i++) {
//            mesaage = Unpooled.buffer(req.length);
//            mesaage.writeBytes(req);
//            ctx.writeAndFlush(mesaage);
//        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
//        System.out.println(msg);
//        byte[] body = (byte[]) msg;
//        HashMap<String, String> result = SerializationUtil.deserialize(body, HashMap.class);
//        String body = (String) msg;
//        System.out.println(body);
//        System.out.println(result.toString());
        System.out.printf("channelRead");
        Response response = (Response) msg;
        HashMap result = (HashMap<String, String>) response.getResult();
        System.out.println(result.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.warning("Unexcepted exception from downsteam : " + cause.getMessage());
        ctx.close();
    }
}
