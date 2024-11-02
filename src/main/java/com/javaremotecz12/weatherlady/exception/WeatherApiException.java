package com.javaremotecz12.weatherlady.exception;

import com.javaremotecz12.weatherlady.model.WeatherApiResponse;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException(String message, WeatherApiResponse cause) {
        super(message,cause);
    }

    public WeatherApiException(String message) {
        super(message);
    }
}
