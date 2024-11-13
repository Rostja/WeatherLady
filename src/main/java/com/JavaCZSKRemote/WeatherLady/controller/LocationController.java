package com.JavaCZSKRemote.WeatherLady.controller;

import com.JavaCZSKRemote.WeatherLady.model.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {
    @GetMapping("/showForm")
    public String showForm(Model theModel){

        Location theLocation = new Location();
        theModel.addAttribute("location",theLocation);

        return "location-form";
    }
}
