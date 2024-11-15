package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather, Long> {
    Optional<CurrentWeather> findByWeatherDataId(Long weatherDataId);
}
