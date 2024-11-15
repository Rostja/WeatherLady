package com.JavaCZSKRemote.WeatherLady.entity;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be greater than or equal to -90")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be less than or equal to 90")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude must be greater than or equal to -180")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be less than or equal to 180")
    private Double longitude;

    private TimeType timeType;
    private UnitSystem unitSystem;

}
