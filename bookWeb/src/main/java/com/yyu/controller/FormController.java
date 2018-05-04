package com.yyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
    @RequestMapping(value = "/")
    public String index(){
        return "loginForm";
    }

    @RequestMapping(value = "/{formName}")
    public String loginForm(@PathVariable String formName){
        return formName;
    }
}
