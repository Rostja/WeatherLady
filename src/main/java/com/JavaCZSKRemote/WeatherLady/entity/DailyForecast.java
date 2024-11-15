package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "daily_forecast")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weather_data_id")
    private WeatherData weatherData;

    @Column(name = "dt")
    private Long timestamp;

    @Column(name = "sunrise")
    private Long sunrise;

    @Column(name = "sunset")
    private Long sunset;

    @Column(name = "temp_day")
    private Double tempDay;

    @Column(name = "temp_min")
    private Double tempMin;

    @Column(name = "temp_max")
    private Double tempMax;

    @Column(name = "temp_night")
    private Double tempNight;

    @Column(name = "weather_description")
    private String weatherDescription;

    @Column(name = "weather_icon")
    private String weatherIcon;

    @OneToOne(mappedBy = "dailyForecast", cascade = CascadeType.ALL)
    private Temp temp;
}
