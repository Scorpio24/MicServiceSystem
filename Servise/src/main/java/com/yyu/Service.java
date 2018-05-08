package com.yyu;

import java.io.IOException;
import java.util.HashMap;

import com.yyu.POJO.IOBody;
import com.yyu.network.Server;
import com.yyu.readConfigFile.ReadConfigFile;
import com.yyu.register.RegisterClient;

public class Service {
    public static void main(String[] args) throws Exception{
        String ip;
        int port;
        if (args != null && args.length == 2)
        {
            ip = args[0];
            port = Integer.valueOf(args[1]);
        }
        else
        {
            System.out.println("Please input the Center's address!");
            return;
        }

        HashMap<String, String> serviceConfig;
        try{
            serviceConfig = new ReadConfigFile().getServiseConfig();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
            return;
        }
        if (!serviceConfig.containsKey("name") || !serviceConfig.containsKey("ip")
                || !serviceConfig.containsKey("port") || !serviceConfig.containsKey("run"))
        {
            System.out.println("Fail to get Service Config!");
            return;
        }

        IOBody IOBody = new RegisterClient().register(ip, port, serviceConfig);
        if (IOBody == null && !("register success!".equals(IOBody.getResult()))){
            System.out.println("fail to register!");
            return;
        }

        new Server().bind(Integer.valueOf(serviceConfig.get("port")));
    }
}
