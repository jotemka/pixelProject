package com.jmk.pixel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeographicPointList {
    @NotNull
    private ArrayList<GeographicPoint> geoPoints;
}