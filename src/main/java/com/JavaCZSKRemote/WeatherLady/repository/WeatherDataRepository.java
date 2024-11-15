package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByLatitudeAndLongitude(Double latitude, Double longitude);
    Optional<WeatherData> findTopByLatitudeAndLongitudeOrderByCreatedAtDesc(Double latitude, Double longitude);
}

