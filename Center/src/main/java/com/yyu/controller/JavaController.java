package com.yyu.controller;

import com.yyu.POJO.IOBody;
import com.yyu.domain.ServiceDO;
import com.yyu.network.ServiceRequestClient;
import com.yyu.service.ServiceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/java")
public class JavaController {

    @Autowired
    private ServiceTableService serviceTableService;

    @RequestMapping(value = "/call", method = RequestMethod.POST)
    IOBody postcall(@RequestBody Map<String,Object> reqMap) throws Exception{
        StringBuffer cmd = new StringBuffer("java -jar");
        HashMap<String, Object> paramMap = new HashMap<>();
        String name;
        String IP;
        String port;
        try {
            name = reqMap.get("name").toString();
            IP = reqMap.get("ip").toString();
            port = reqMap.get("port").toString();
        }catch (Exception e)
        {
            IOBody error = new IOBody();
            error.setResult("Error:参数错误。");
            return error;
        }
        paramMap.put("serviceName", name);
        paramMap.put("serviceIP", IP);
        paramMap.put("servicePort", port);

        List<ServiceDO> serviceDOList = serviceTableService.list(paramMap);
        if (serviceDOList.isEmpty()){
            IOBody error = new IOBody();
            error.setResult("Error:没有该服务。");
            return error;
        }

        cmd.append(name + ".jar");
        int paramNumber = reqMap.size() - 3;
        for (int i = 1; i <= paramNumber; i++)
        {
            String param = reqMap.get("param" + i).toString();
            cmd.append(" " + param);
        }

        ServiceRequestClient serviceRequestClient = new ServiceRequestClient();
        return serviceRequestClient.execute(Integer.parseInt(port), IP, cmd.toString(), serviceDOList.get(0).getServiceStatus());
    }

}