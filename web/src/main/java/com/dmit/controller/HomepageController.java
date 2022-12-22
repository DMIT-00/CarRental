package com.dmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomepageController {
    @GetMapping({"/", "index"})
    public String homepage(ModelAndView modelAndView) {
        modelAndView.addObject("greeting", "Hello, User!");
        return "index";
    }
}
