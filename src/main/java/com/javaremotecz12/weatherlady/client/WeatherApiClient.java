package com.javaremotecz12.weatherlady.client;

import com.javaremotecz12.weatherlady.exception.WeatherApiException;
import com.javaremotecz12.weatherlady.mapper.WeatherDataMapper;
import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherApiResponse;
import com.javaremotecz12.weatherlady.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherApiClient {
    private final String apiKey;
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private final WeatherDataMapper weatherDataMapper;

    public WeatherApiClient(
            @Value("${weather.api.key}") String apiKey,
            @Value("${weather.api.url}") String apiUrl,
            RestTemplate restTemplate,
            WeatherDataMapper weatherDataMapper) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.weatherDataMapper = weatherDataMapper;
    }

    public WeatherData getWeatherData(Location location) {
        validateLocation(location);

        String url = buildApiUrl(location);

        try {
            log.debug("Calling weather API for location: {}", location.getCity());
            WeatherApiResponse response = fetchWeatherData(url);
            validateResponse(response);
            return weatherDataMapper.fromApiResponse(response, location);
        } catch (RestClientException e) {
            throw handleApiError(e);
        }
    }

    private void validateLocation(Location location) {
        if (location == null || location.getLatitude() < -90 || location.getLatitude() > 90) {
            log.error("Invalid location provided: {}", location);
            throw new IllegalArgumentException("Location coordinates cannot be null");
        }
    }

    private String buildApiUrl(Location location) {
        return String.format("%s/current.json?key=%s&q=%f,%f",
                apiUrl,
                apiKey,
                location.getLatitude(),
                location.getLongitude());
    }

    private WeatherApiResponse fetchWeatherData(String url) {
        log.debug("Calling weather API with URL: {}", url);
        return restTemplate.getForObject(url, WeatherApiResponse.class);
    }

    private void validateResponse(WeatherApiResponse response) {
        if (response == null) {
            log.error("Received null response from weather API");
            throw new WeatherApiException("Received null response from the weather API");
        }
    }

    private WeatherApiException handleApiError(Exception e) {
        log.error("Error fetching weather data: {}", e.getMessage());
        return new WeatherApiException("Failed to fetch weather data");
    }
}