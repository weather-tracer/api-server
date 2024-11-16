package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AdministrativeArea {
    @JsonProperty("ID")
    private String id;

    @JsonProperty("LocalizedName")
    private String localizedName;

    @JsonProperty("EnglishName")
    private String englishName;

    @JsonProperty("Level")
    private int level;

    @JsonProperty("LocalizedType")
    private String localizedType;

    @JsonProperty("EnglishType")
    private String englishType;

    @JsonProperty("CountryID")
    private String countryID;
}
