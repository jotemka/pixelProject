package com.jmk.pixel.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeographicPoint {

    @NotNull
    Double latitude;
    @NotNull
    Double longitude;
    @NotNull
    String name;

    public GeographicPoint(Double latitude, Double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public GeographicPoint() {
    }
}
