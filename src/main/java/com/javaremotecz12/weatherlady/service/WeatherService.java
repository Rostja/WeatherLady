package com.javaremotecz12.weatherlady.service;

import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import com.javaremotecz12.weatherlady.repository.LocationRepository;
import com.javaremotecz12.weatherlady.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    WeatherDataRepository weatherDataRepository;

    @Autowired
    private WeatherApiClient weatherApiClient;

    public Location addLocation(Location location){
        validateLocation(location);
        return locationRepository.save(location);
    }

    private void validateLocation(Location location){
        if (location.getLatitude() < -90 || location.getLatitude() > 90){
            throw new IllegalArgumentException("Invalid latitude");
        }
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();

    }
    public WeatherData weatherData(String city, LocalDate date){
        Location location = locationRepository.findByCity(city)
        .orElseThrow(() -> new RuntimeException("Location not found"));

        return weatherApiClient.getWeatherData(location,date);

    }

    public List<WeatherData> getWeatherForLocation(Location location, LocalDate startDate, LocalDate endDate){
        return weatherDataRepository.findByLocationAndDateBetween(location, startDate, endDate);
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }

    public void updateLocation(Location location){
        validateLocation(location);
        locationRepository.save(location);
    }

}
