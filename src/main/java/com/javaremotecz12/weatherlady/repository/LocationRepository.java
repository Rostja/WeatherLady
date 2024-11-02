package com.javaremotecz12.weatherlady.repository;

import com.javaremotecz12.weatherlady.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Override
    Optional<Location> findById(Long aLong);
    Optional<Location> findByCity(String city);
    Optional<Location> findByCountry(String country);

}
