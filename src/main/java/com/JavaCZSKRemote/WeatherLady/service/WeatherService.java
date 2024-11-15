package com.JavaCZSKRemote.WeatherLady.service;


import com.JavaCZSKRemote.WeatherLady.entity.WeatherData;
import com.JavaCZSKRemote.WeatherLady.entity.Location;

public interface WeatherService {
    WeatherData getWeatherData(Location location);
    WeatherData getLatestWeatherData(Double latitude, Double longitude);
    void saveWeatherData(WeatherData weatherData);
    void deleteOldWeatherData(int daysOld);
}
