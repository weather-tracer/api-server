package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Elevation {
    @JsonProperty("Metric")
    private UnitValue metric;

    @JsonProperty("Imperial")
    private UnitValue imperial;
}