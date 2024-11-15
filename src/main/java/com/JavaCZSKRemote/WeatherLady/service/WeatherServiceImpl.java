package com.JavaCZSKRemote.WeatherLady.service;

import com.JavaCZSKRemote.WeatherLady.entity.*;
import com.JavaCZSKRemote.WeatherLady.entity.Location;
import com.JavaCZSKRemote.WeatherLady.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

/**
 * Service implementation for weather-related operations.
 * Fetches weather data from an external API and stores it in the database.
 * Implements the WeatherService interface.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String API_URL;

    /**
     * Spring's RestTemplate for making HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Repository for managing weather data in the database.
     */
    private final WeatherDataRepository weatherDataRepository;

    /**
     * Constructor for WeatherServiceImpl.
     *
     * @param restTemplate       Spring's RestTemplate for making HTTP requests.
     * @param weatherDataRepository Repository for managing weather data in the database.
     */
    public WeatherServiceImpl(RestTemplate restTemplate, WeatherDataRepository weatherDataRepository) {
        this.restTemplate = restTemplate;
        this.weatherDataRepository = weatherDataRepository;
    }

    /**
     * Retrieves weather data for a given location. If the data is less than 30 minutes old,
     * it returns the latest data from the database. Otherwise, it fetches the data from the external API,
     * stores it in the database, and returns the new data.
     *
     * @param location The location for which to retrieve weather data.
     * @return The weather data for the specified location.
     * @throws RuntimeException If no weather data is found for the location.
     */
    @Override
    public WeatherData getWeatherData(Location location) {

        Optional<WeatherData> latestData = weatherDataRepository
                .findTopByLatitudeAndLongitudeOrderByCreatedAtDesc(
                        location.getLatitude(),
                        location.getLongitude()
                );

        // If we have data less than 30 minutes old, return them
        if (latestData.isPresent() && isDataFresh(latestData.get())) {
            return latestData.get();
        }

        // If not, fetch new data from API
        String url = buildApiUrl(location);
        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);

        if (weatherData != null) {
            weatherData.setCreatedAt(new Date());
            weatherDataRepository.save(weatherData);
        }

        return weatherData;
    }

    /**
     * Retrieves the latest weather data for a given location from the database.
     * If no data is found, it throws a RuntimeException.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The latest weather data for the specified location.
     * @throws RuntimeException If no weather data is found for the location.
     */
    @Override
    public WeatherData getLatestWeatherData(Double latitude, Double longitude) {
        return weatherDataRepository
                .findTopByLatitudeAndLongitudeOrderByCreatedAtDesc(latitude, longitude)
                .orElseThrow(() -> new RuntimeException("No weather data found for location"));
    }

    /**
     * Saves the given weather data to the database.
     *
     * @param weatherData The weather data to be saved.
     */
    @Override
    public void saveWeatherData(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }

    /**
     * Deletes weather data from the database that is older than the specified number of days.
     *
     * @param daysOld The number of days old weather data to delete.
     */
    @Override
    public void deleteOldWeatherData(int daysOld) {
        Date cutoffDate = Date.from(Instant.now().minus(daysOld, ChronoUnit.DAYS));
        weatherDataRepository.deleteByCreatedAtLessThan(cutoffDate);
    }

    /**
     * Builds a URL for fetching weather data from the external API based on the given location.
     *
     * @param location The location for which to build the API URL.
     * @return The API URL for fetching weather data.
     */
    private String buildApiUrl(Location location) {
        return String.format("%s?lat=%f&lon=%f&appid=%s&units=%s&lang=%s",
                API_URL,
                location.getLatitude(),
                location.getLongitude(),
                apiKey,
                location.getUnitSystem().getApiValue(),
                "en"
        );
    }

    private boolean isDataFresh(WeatherData data) {
        long thirtyMinutesInMs = 30 * 60 * 1000;
        return (new Date().getTime() - data.getCreatedAt().getTime()) < thirtyMinutesInMs;
    }
}
