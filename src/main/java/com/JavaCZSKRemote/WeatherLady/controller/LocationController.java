package com.JavaCZSKRemote.WeatherLady.controller;

import com.JavaCZSKRemote.WeatherLady.model.Location;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LocationController {

    @Value("${languages}")
    private List<String> languages;

    @GetMapping("/showLocationForm")
    public String showLocationForm(Model theModel) {

        Location theLocation = new Location();
        theModel.addAttribute("location", theLocation);

        theModel.addAttribute("languages", languages);
        return "location-form";
    }

    @PostMapping("/processLocationForm")
    public String processLocationForm(
            @Valid @ModelAttribute("location") Location theLocation,
            BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()){
            return "customer-form";
        } else {
            System.out.println("theLocation: "
                    + theLocation.getLatitude() + " " + theLocation.getLongitude());

            return "location-confirmation";
        }

    }
}
