package com.JavaCZSKRemote.WeatherLady.controller;

import com.JavaCZSKRemote.WeatherLady.model.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {
    @GetMapping("/showForm")
    public String showForm(Model theModel) {

        Location theLocation = new Location();
        theModel.addAttribute("location", theLocation);

        return "location-form";
    }

    @PostMapping("/processLocationForm")
    public String processLocationForm(@ModelAttribute("location") Location theLocation) {
        System.out.println("theLocation: "
                + theLocation.getLatitude() + " " + theLocation.getLongitude());

        return "location-confirmation";
    }
}
