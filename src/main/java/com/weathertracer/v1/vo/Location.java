package com.weathertracer.v1.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Location {
    @JsonProperty("Version")
    private int version;

    @JsonProperty("Key")
    private String key;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Rank")
    private int rank;

    @JsonProperty("LocalizedName")
    private String localizedName;

    @JsonProperty("EnglishName")
    private String englishName;

    @JsonProperty("PrimaryPostalCode")
    private String primaryPostalCode;

    @JsonProperty("Region")
    private Region region;

    @JsonProperty("Country")
    private Country country;

    @JsonProperty("AdministrativeArea")
    private AdministrativeArea administrativeArea;

    @JsonProperty("TimeZone")
    private TimeZone timeZone;

    @JsonProperty("GeoPosition")
    private GeoPosition geoPosition;

    @JsonProperty("IsAlias")
    private boolean isAlias;

    @JsonProperty("ParentCity")
    private ParentCity parentCity;

    @JsonProperty("SupplementalAdminAreas")
    private List<SupplementalAdminArea> supplementalAdminAreas;

    @JsonProperty("DataSets")
    private List<String> dataSets;
}
