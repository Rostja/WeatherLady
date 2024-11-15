package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "hourly_forecast")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weather_data_id")
    private WeatherData weatherData;

    @Column(name = "dt")
    private Long timestamp;

    @Column(name = "temp")
    private Double temperature;

    @Column(name = "feels_like")
    private Double feelsLike;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "weather_description")
    private String weatherDescription;

    @Column(name = "weather_icon")
    private String weatherIcon;
}
