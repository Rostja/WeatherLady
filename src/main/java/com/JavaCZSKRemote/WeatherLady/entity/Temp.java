package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "temp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temp {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "daily_id")
    private DailyForecast dailyForecast;

    @Column(name = "day")
    private Double day;

    @Column(name = "min")
    private Double min;

    @Column(name = "max")
    private Double max;

    @Column(name = "night")
    private Double night;

    @Column(name = "eve")
    private Double eve;

    @Column(name = "morn")
    private Double morn;
}