package com.JavaCZSKRemote.WeatherLady.entity;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "weather_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude",nullable = false)
    private Double longitude;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "timezone_offset")
    private Integer timezoneOffset;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    private CurrentWeather currentWeather;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weatherData")
    private List<HourlyForecast> hourlyForecasts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weatherData")
    private List<DailyForecast> dailyForecasts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weatherData")
    private List<WeatherAlert> alerts;
}

