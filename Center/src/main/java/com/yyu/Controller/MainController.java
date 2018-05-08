package com.yyu.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController{

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String hello(){
        return "Hello!";
    }

}
