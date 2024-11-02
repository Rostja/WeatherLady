package com.javaremotecz12.weatherlady.client;

import com.javaremotecz12.weatherlady.exception.WeatherApiException;
import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherApiClient {
    @Value("${accuweather.api.key}")
    private String apiKey;

    @Value("${accuweather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherData(Location location) {
        String url = String.format("%s/current.json?key=%s&q=%f%f",
                apiUrl, apiKey, location.getLatitude(), location.getLongitude());

        try{
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            return convertToWeatherData(response, location);
        } catch (RestClientException e) {
        log.error("Error fetching weather data: {}", e.getMessage());
        throw new WeatherApiException("Failed to fetch weather data", e);
        }
    }
}
