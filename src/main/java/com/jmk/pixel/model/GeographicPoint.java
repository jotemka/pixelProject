package com.jmk.pixel.model;

import lombok.Data;

@Data
public class GeographicPoint {

    Double latitude;
    Double longitude;
    String name;

    public GeographicPoint(Double latitude, Double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public GeographicPoint() {
    }
}
