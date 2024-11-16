package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TimeZone {
    @JsonProperty("Code")
    private String code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("GmtOffset")
    private double gmtOffset;

    @JsonProperty("IsDaylightSaving")
    private boolean isDaylightSaving;

    @JsonProperty("NextOffsetChange")
    private Object nextOffsetChange; // Can be of type Date or null
}