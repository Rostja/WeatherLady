package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempRepository extends JpaRepository<Temp, Long> {
    Optional<Temp> findByDailyForecastId(Long dailyId);
}
