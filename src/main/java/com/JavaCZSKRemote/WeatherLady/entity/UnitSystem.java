package com.JavaCZSKRemote.WeatherLady.entity;

import lombok.Getter;

@Getter
public enum UnitSystem {
    STANDARD("standard", "Kelvin, m/s", "K"),
    METRIC("metric", "Celsius, m/s", "°C"),
    IMPERIAL("imperial", "Fahrenheit, mph", "°F");

    private final String apiValue;
    private final String description;
    private final String tempSymbol;

    UnitSystem(String apiValue, String description, String tempSymbol) {
        this.apiValue = apiValue;
        this.description = description;
        this.tempSymbol = tempSymbol;
    }

}
