package com.yyu.network;

import com.yyu.POJO.IOBody;
import com.yyu.domain.ServiceDO;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Logger;

public class ServiceRequestClient extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ServiceRequestClient.class.getName());

    private IOBody IOBody = new IOBody();
    private String cmd;
    private String nowstatus = "error";

    public IOBody execute(int port, String host, String cmdStr, String status) throws Exception{
        cmd = cmdStr;
        connect(port, host);
//        if (!nowstatus.equals(status)){
//            update(nowstatus);
//        }
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
        this.nowstatus = "run";
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.warning("Unexcepted exception from downsteam : " + cause.getMessage());
        ctx.close();
    }

    private void update(String status) throws IOException {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceStatus(status);
        serviceDO.setServiceIP("127.0.0.1");
        serviceDO.setServicePort("8080");
        serviceDO.setServiceName("test");

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        session.update("com.yyu.dao.ServiceDao.update", serviceDO);
        session.commit();
        session.close();
    }
}
