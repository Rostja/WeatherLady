package com.JavaCZSKRemote.WeatherLady.controller;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/processFormVersionTwo")
    public String processFormVersionTwo(HttpServletRequest request, Model theModel) {
        String coordinates =request.getParameter("latitude" + " and " + "longitude");
        coordinates = coordinates.toString();
        String result = "My coordinates are " + coordinates;

        theModel.addAttribute("theDate", java.time.LocalDateTime.now());
        theModel.addAttribute("message", result);

        return "main";
    }
}
