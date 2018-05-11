package com.yyu.controller;

import com.yyu.POJO.IOBody;
import com.yyu.network.ServiceRequestClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/python")
public class PythonController {

    @RequestMapping("/{pname}")
    IOBody call(@PathVariable("pname") String pname) throws Exception{
        String cmd = "python D:/test/" + pname + ".py";
        ServiceRequestClient serviceRequestClient = new ServiceRequestClient();
        return serviceRequestClient.execute(8080, "127.0.0.1", cmd);
    }

}
