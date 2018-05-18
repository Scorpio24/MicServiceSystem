package com.yyu.network;

import com.yyu.POJO.IOBody;
import com.yyu.domain.ServiceDO;
import com.yyu.service.ServiceTableService;
import com.yyu.service.impl.ServiceTableServiceImpl;
import com.yyu.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

public class RegisterServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
//        System.out.printf("serverhandler");
        IOBody body = (IOBody) msg;
//        System.out.println(body.getResult().toString());
        IOBody IOBody = new IOBody();
        HashMap<String, Object> serviceConfig = (HashMap) body.getResult();
        System.out.println(serviceConfig);

        if ("register".equals(serviceConfig.get("run")))
        {
            try {
                save(serviceConfig);
                IOBody.setResult("register success!");
            }
            catch (Exception e)
            {
//                e.printStackTrace();
                IOBody.setResult("register success!");
            }
        }

        if ("stop".equals(serviceConfig.get("run")))
        {
            try {
                delete(serviceConfig);
                IOBody.setResult("stop success!");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
//        System.out.println("111");

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

    private byte[] byteArrayMerge(byte[] byteArray1, byte[] byteArray2){
        byte[] resultData = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, resultData, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, resultData, byteArray1.length, byteArray2.length);
        return resultData;
    }

    private void save(HashMap<String, Object> serviceConfig) throws Exception {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceIP((String)serviceConfig.get("ip"));
        serviceDO.setServiceName((String)serviceConfig.get("name"));
        serviceDO.setServiceType((String)serviceConfig.get("type"));
        serviceDO.setServicePort((String)serviceConfig.get("port"));
        serviceDO.setServiceParam(serviceConfig.get("paramFormat").toString());
        serviceDO.setServiceResult(serviceConfig.get("resultFormat").toString());
        serviceDO.setServiceStatus("run");

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("com.yyu.dao.ServiceDao.save", serviceDO);
        session.commit();
        session.close();
    }

    private void delete(HashMap<String, Object> serviceConfig) throws Exception {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceIP((String)serviceConfig.get("ip"));
        serviceDO.setServiceName((String)serviceConfig.get("name"));
        serviceDO.setServicePort((String)serviceConfig.get("port"));
        serviceDO.setServiceParam(serviceConfig.get("paramFormat").toString());
        serviceDO.setServiceResult(serviceConfig.get("resultFormat").toString());

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("com.yyu.dao.ServiceDao.remove", serviceDO);
        session.commit();
        session.close();
    }
}
