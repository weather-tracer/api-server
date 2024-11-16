package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Direction {
    @JsonProperty("Degrees")
    private int degrees;

    @JsonProperty("Localized")
    private String localized;

    @JsonProperty("English")
    private String english;
}
