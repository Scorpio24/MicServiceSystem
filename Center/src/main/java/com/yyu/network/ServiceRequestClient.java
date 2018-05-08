package com.yyu.network;

import com.yyu.POJO.IOBody;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.util.logging.Logger;

public class ServiceRequestClient extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ServiceRequestClient.class.getName());

    private IOBody IOBody;
    private String cmd;

    public IOBody execute(int port, String host, String cmdStr) throws Exception{
        cmd = cmdStr;
        connect(port, host);
        return IOBody;
    }

    private void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception{
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new IOBodyDecoder(IOBody.class));
                            ch.pipeline().addLast(ServiceRequestClient.this);
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
        byte[] req = (cmd + System.getProperty("line.separator")).getBytes();
        ByteBuf message = null;
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
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
