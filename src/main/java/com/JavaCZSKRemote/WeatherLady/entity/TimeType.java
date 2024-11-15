package com.JavaCZSKRemote.WeatherLady.entity;

import lombok.Getter;


@Getter

public enum TimeType {
    CURRENT("current", "Aktuálne počasie"),
    MINUTELY("minutely", "Minútová predpoveď na 1 hodinu"),
    HOURLY("hourly", "Hodinová predpoveď na 48 hodín"),
    DAILY("daily", "Denná predpoveď na 8 dní"),
    ALERTS("alerts", "Výstrahy počasia");

    private final String apiValue;
    private final String description;

    TimeType(String apiValue, String description) {
        this.apiValue = apiValue;
        this.description = description;
    }

}
