package com.JavaCZSKRemote.WeatherLady.repository;

import com.JavaCZSKRemote.WeatherLady.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
    List<Location> findByNameContainingIgnoreCase(String name);
}
