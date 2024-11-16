package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Wind {
    @JsonProperty("Speed")
    private UnitValue speed;

    @JsonProperty("Direction")
    private Direction direction;
}