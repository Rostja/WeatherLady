package com.JavaCZSKRemote.WeatherLady.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherLadyController {

    @RequestMapping("/showForm")
    public String showForm(){

        return "main-form";
    }

    @GetMapping("/processForm")
    public String processForm(Model theModel) {
        theModel.addAttribute("theDate", java.time.LocalDateTime.now());

        return "main";
    }
}
