package com.rebellion.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @GetMapping("")
    public String getHomePage() {
        return "homepage";
    }

    @GetMapping("article/{id}")
    public ModelAndView getMethodName(@PathVariable int id, ModelAndView modelAndView) {
        modelAndView.addObject("id", id);
        modelAndView.setViewName("articleview");
        return modelAndView;
    }
    
}
