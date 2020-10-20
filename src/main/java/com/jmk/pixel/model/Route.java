package com.jmk.pixel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    ArrayList<RoutePoint> routePoints;
    Double routeLength;
}
