package com.weathertracer.v1.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Forecast {
    @JsonProperty("DateTime")
    private String dateTime;

    @JsonProperty("EpochDateTime")
    private long epochDateTime;

    @JsonProperty("WeatherIcon")
    private int weatherIcon;

    @JsonProperty("IconPhrase")
    private String iconPhrase;

    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;

    @JsonProperty("IsDaylight")
    private boolean isDaylight;

    @JsonProperty("Temperature")
    private UnitValue temperature;

    @JsonProperty("RealFeelTemperature")
    private UnitValue realFeelTemperature;

    @JsonProperty("RealFeelTemperatureShade")
    private UnitValue realFeelTemperatureShade;

    @JsonProperty("WetBulbTemperature")
    private UnitValue wetBulbTemperature;

    @JsonProperty("WetBulbGlobeTemperature")
    private UnitValue wetBulbGlobeTemperature;

    @JsonProperty("DewPoint")
    private UnitValue dewPoint;

    @JsonProperty("Wind")
    private Wind wind;

    @JsonProperty("WindGust")
    private WindGust windGust;

    @JsonProperty("RelativeHumidity")
    private int relativeHumidity;

    @JsonProperty("IndoorRelativeHumidity")
    private int indoorRelativeHumidity;

    @JsonProperty("Visibility")
    private UnitValue visibility;

    @JsonProperty("Ceiling")
    private UnitValue ceiling;

    @JsonProperty("UVIndex")
    private int uvIndex;

    @JsonProperty("UVIndexText")
    private String uvIndexText;

    @JsonProperty("PrecipitationProbability")
    private int precipitationProbability;

    @JsonProperty("ThunderstormProbability")
    private int thunderstormProbability;

    @JsonProperty("RainProbability")
    private int rainProbability;

    @JsonProperty("SnowProbability")
    private int snowProbability;

    @JsonProperty("IceProbability")
    private int iceProbability;

    @JsonProperty("TotalLiquid")
    private UnitValue totalLiquid;

    @JsonProperty("Rain")
    private UnitValue rain;

    @JsonProperty("Snow")
    private UnitValue snow;

    @JsonProperty("Ice")
    private UnitValue ice;

    @JsonProperty("CloudCover")
    private int cloudCover;

    @JsonProperty("Evapotranspiration")
    private UnitValue evapotranspiration;

    @JsonProperty("SolarIrradiance")
    private UnitValue solarIrradiance;

    @JsonProperty("MobileLink")
    private String mobileLink;

    @JsonProperty("Link")
    private String link;
}