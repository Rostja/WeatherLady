package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "weather_alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weather_data_id")
    private WeatherData weatherData;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "event")
    private String event;

    @Column(name = "start")
    private Long start;

    @Column(name = "end")
    private Long end;

    @Column(name = "description", length = 1000)
    private String description;
}
