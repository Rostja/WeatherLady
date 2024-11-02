package com.javaremotecz12.weatherlady.test;

import com.javaremotecz12.weatherlady.client.WeatherApiClient;
import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherApiClient weatherApiClient;

    @Test
    void whenAddLocation_thenLocationIsSaved() {
        Location location = new Location();
        location.setCity("Prague");
        location.setLatitude(50.0755);
        location.setLongitude(14.4378);

        Location savedLocation = weatherService.addLocation(location);

        assertNotNull(savedLocation.getId());
        assertEquals("Prague", savedLocation.getCity());
    }
}
