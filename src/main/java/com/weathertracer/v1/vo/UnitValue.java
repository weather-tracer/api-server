package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnitValue {
    @JsonProperty("Value")
    private float value;

    @JsonProperty("Unit")
    private String unit;

    @JsonProperty("UnitType")
    private int unitType;
}
