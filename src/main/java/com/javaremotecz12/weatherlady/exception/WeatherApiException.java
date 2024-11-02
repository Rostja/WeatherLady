package com.javaremotecz12.weatherlady.exception;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException(String message,Throwable cause) {
        super(message,cause);
    }
}
