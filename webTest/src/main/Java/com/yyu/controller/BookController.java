package com.yyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @RequestMapping("/main")
    public String main(Model model){
        List<String> book_list = new ArrayList<>();
        book_list.add("book1");
        book_list.add("book2");
        book_list.add("book3");
        book_list.add("book4");
        model.addAttribute("book_list", book_list);
        return "main";
    }
}
