package com.yyu.controller;

import com.yyu.domain.ServiceDO;
import com.yyu.service.ServiceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController{

    @Autowired
    private ServiceTableService serviceTableService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/list")
    public String index(Model model) {
        List<ServiceDO> serviceDOList = serviceTableService.get();
        model.addAttribute("serviceDOList", serviceDOList);
        return "/list";
    }

//    @RequestMapping(value = "/save")
//    public void save(@RequestParam("ip") String ip,
//                     @RequestParam("name") String name,
//                     @RequestParam("port") String port,
//                     @RequestParam("paramFormat") String paramFormat,
//                     @RequestParam("resultFormat") String resultFormat){
//        ServiceDO serviceDO = new ServiceDO();
//        System.out.println("config2do");
//
//        serviceDO.setServiceIP(ip);
//        serviceDO.setServiceName(name);
//        serviceDO.setServicePort(port);
//        serviceDO.setServiceParam(paramFormat);
//        serviceDO.setServiceResult(resultFormat);
//
//        serviceTableService.save(serviceDO);
//    }

}
