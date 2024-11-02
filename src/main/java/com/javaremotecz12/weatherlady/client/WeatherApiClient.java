package com.javaremotecz12.weatherlady.client;

import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class WeatherApiClient {
    private final RestTemplate restTemplate;
    private final String apiKey = "kde8TxGwQAHaacCpZnGEpiWguSDFJDO9";


    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherData(Location location, LocalDate date) {
        String url = String.format(
                "https://api.weatherservice.com/data?lat=%f&lon=%f&date=%s&apikey=%s",
                location.getLatitude(),
                location.getLongitude(),
                date,
                apiKey
        );
        WeatherApiResponse response = restTemplate.getForObject(url,WeatherApiResponse.class){
            return convertToWeatherData(response, location);
        }
    }
}
