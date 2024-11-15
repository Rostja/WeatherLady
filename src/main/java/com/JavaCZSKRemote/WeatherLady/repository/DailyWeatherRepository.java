package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.DailyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyWeatherRepository extends JpaRepository<DailyForecast, Long> {
    List<DailyForecast> findByWeatherDataIdOrderByDtAsc(Long weatherDataId);
}
