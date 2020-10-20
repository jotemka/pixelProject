package com.jmk.pixel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicPoint {

    @NotNull
    Double latitude;
    @NotNull
    Double longitude;
    @NotNull
    String name;
}
