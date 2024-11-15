package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.HourlyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourlyWeatherRepository extends JpaRepository<HourlyForecast, Long> {
    List<HourlyForecast> findByWeatherDataIdOrderByDtAsc(Long weatherDataId);
}
