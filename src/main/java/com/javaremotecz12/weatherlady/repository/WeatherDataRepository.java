package com.javaremotecz12.weatherlady.repository;

import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData,Long> {
    @Override
    List<WeatherData> findAll();
    List<WeatherData> findByCityAndDateBetween(String city, String startDate, String endDate);
    List<WeatherData> findByCity(String city);
    List<WeatherData> findByLocationAndTimestampBetween(
            Location location,
            LocalDateTime start,
            LocalDateTime end
    );


}
