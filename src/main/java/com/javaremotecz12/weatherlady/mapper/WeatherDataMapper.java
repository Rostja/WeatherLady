package com.javaremotecz12.weatherlady.mapper;


import org.springframework.stereotype.Component;
import com.javaremotecz12.weatherlady.model.WeatherData;
import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherApiResponse;

import java.time.LocalDateTime;

@Component
public class WeatherDataMapper {
    public WeatherData fromApiResponse(WeatherApiResponse response, Location location) {
        return WeatherData.builder()
                .temperature(response.getTemperature())
                .humidity(response.getHumidity())
                .pressure(response.getPressure())
                .date(LocalDateTime.now())
                .location(location)
                .build();
    }
}
