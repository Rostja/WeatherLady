package com.JavaCZSKRemote.WeatherLady.model;

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

    public String getApiValue() {
        return apiValue;
    }

    public String getDescription() {
        return description;
    }

    public String getTempSymbol() {
        return tempSymbol;
    }
}
