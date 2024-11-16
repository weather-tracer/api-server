package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WindGust {
    @JsonProperty("Speed")
    private UnitValue speed;
}