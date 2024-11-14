package com.JavaCZSKRemote.WeatherLady.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @NotNull(message="latitude is required")
    @Size(min=1, message="latitude is required")
    private Double latitude;

    @NotNull(message="longitude is required")
    @Size(min=1, message="longitude is required")
    private Double longitude;

    private TimeType timeType;
    private UnitSystem unitSystem;

}
