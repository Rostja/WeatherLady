package com.javaremotecz12.weatherlady.service;


import com.javaremotecz12.weatherlady.client.WeatherApiClient;
import com.javaremotecz12.weatherlady.exception.LocationNotFoundException;
import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import com.javaremotecz12.weatherlady.repository.LocationRepository;
import com.javaremotecz12.weatherlady.repository.WeatherDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherService {
    private final LocationRepository locationRepository;
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherApiClient weatherApiClient;

    @Autowired
    public WeatherService(LocationRepository locationRepository,
                          WeatherDataRepository weatherDataRepository,
                          WeatherApiClient weatherApiClient) {
        this.locationRepository = locationRepository;
        this.weatherDataRepository = weatherDataRepository;
        this.weatherApiClient = weatherApiClient;
    }

    public Location addLocation(Location location){
        log.info("Adding new location: {}", location.getCity());
        return locationRepository.save(location);
            }
    public WeatherData getWeatherData(String city) {
        log.debug("Fetching weather data for city: {}", city);
        Location location = locationRepository.findByCity(city)
                .orElseThrow(() -> {
                    log.error("Location not found for city: {}", city);
                    return new LocationNotFoundException(city);
                });
        WeatherData weatherData = weatherApiClient.getWeatherData(location);
        return weatherDataRepository.save(weatherData);  // Save the weather data before returning
    }
}
