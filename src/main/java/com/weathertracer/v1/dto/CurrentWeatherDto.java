package com.weathertracer.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeatherDto {
    private String address;
    private String measurementTime;
    private float temperature;
    private int sky;
    private float rainChance;
}
