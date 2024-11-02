package com.javaremotecz12.weatherlady.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiResponse extends Throwable {
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;
    private String windDirection;
}
