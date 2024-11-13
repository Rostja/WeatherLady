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
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        double[] coordinates = new double[2];

        try {
            coordinates[0] = Double.parseDouble(latitude);
            coordinates[1] = Double.parseDouble(longitude);

            String result = String.format("My coordinates are:  latitude = %.6f, longitude = %.6f", coordinates[0], coordinates[1]);

            theModel.addAttribute("theDate", java.time.LocalDateTime.now());
            theModel.addAttribute("message", result);
        } catch (NumberFormatException e) {
            theModel.addAttribute("message", "Invalid coordinates format");
        }

        return "main";
    }
}
