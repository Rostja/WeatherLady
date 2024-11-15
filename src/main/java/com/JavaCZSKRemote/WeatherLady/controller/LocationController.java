package com.JavaCZSKRemote.WeatherLady.controller;

import com.JavaCZSKRemote.WeatherLady.entity.Location;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude,
            Model theModel,

            @Valid @ModelAttribute("location") Location theLocation,
            BindingResult theBindingResult) {
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

        System.out.println("Binding results: " + theBindingResult.toString());
        System.out.println("\n\n\n");

        if (theBindingResult.hasErrors()){
            return "location-form";
        } else {
            System.out.println("theLocation: "
                    + theLocation.getLatitude() + " " + theLocation.getLongitude());

            return "location-confirmation";
        }

    }
}
