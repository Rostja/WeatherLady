package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "current_weather")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dt")
    private Long timestamp;

    @Column(name = "sunrise")
    private Long sunrise;

    @Column(name = "sunset")
    private Long sunset;

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

    @Column(name = "wind_deg")
    private Integer windDeg;

    @Column(name = "clouds")
    private Integer clouds;

    @Column(name = "weather_description")
    private String weatherDescription;

    @Column(name = "weather_icon")
    private String weatherIcon;
}
