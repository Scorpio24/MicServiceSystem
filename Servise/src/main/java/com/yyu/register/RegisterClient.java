package com.yyu.register;

import com.yyu.POJO.IOBody;
import com.yyu.Util.SerializationUtil;
import com.yyu.network.IOBodyDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.util.HashMap;
import java.util.logging.Logger;

public class RegisterClient extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(RegisterClient.class.getName());

    private IOBody IOBody;
    private IOBody serviceConfig = new IOBody();

    public IOBody register(String host, int port, HashMap<String, Object> serviceConfig){
        this.serviceConfig.setResult(serviceConfig);
        try {
            connect(port, host);
        }catch (Exception e1)
        {
            e1.printStackTrace();
        }
        return IOBody;
    }

    private void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
//                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception{
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new IOBodyDecoder(IOBody.class));
                            ch.pipeline().addLast(RegisterClient.this);
                        }
                    });

            ChannelFuture f = b.connect(host, port).sync();

            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        byte[] resultBytes = SerializationUtil.serialize(serviceConfig);
        byte[] lineBytes = System.getProperty("line.separator").getBytes();

        byte[] resultData = this.byteArrayMerge(resultBytes, lineBytes);

        ByteBuf resp = Unpooled.copiedBuffer(resultData);
        ctx.writeAndFlush(resp);
    }

    private byte[] byteArrayMerge(byte[] byteArray1, byte[] byteArray2){
        byte[] resultData = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, resultData, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, resultData, byteArray1.length, byteArray2.length);
        return resultData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        this.IOBody = (IOBody) msg;
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.warning("Unexcepted exception from downsteam : " + cause.getMessage());
        ctx.close();
    }
}
