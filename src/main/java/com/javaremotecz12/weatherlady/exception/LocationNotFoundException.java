package com.javaremotecz12.weatherlady.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(String city) {
        super("Location with city " + city + " not found");
    }
}
