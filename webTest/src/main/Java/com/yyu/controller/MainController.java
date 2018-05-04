package com.yyu.controller;

import domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainController {
    private static final Log logger = LogFactory.getLog(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/{formName}")
    public String loginForm(
            @PathVariable String formName,
            Model model
    ){
        User user = new User();
        model.addAttribute("user", user);
        return formName;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(
//            @Valid @ModelAttribute User user,
//            Errors errors,
//            Model model
//            )
//    {
//        logger.info(user);
//        if (errors.hasErrors())
//        {
//            return "registerForm";
//        }
//        model.addAttribute("user", user);
//        return "success";
//    }
}
