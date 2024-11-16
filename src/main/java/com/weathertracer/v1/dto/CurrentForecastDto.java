package com.weathertracer.v1.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "날씨 정보")
@Data
public class CurrentForecastDto {
    @Schema(description = "주소")
    @JsonProperty("Address")
    private String address;

    @Schema(description = "날씨 조회 시간 (UTC 초)")
    @JsonProperty("EpochSecond")
    private long epochSecond;

    @Schema(description = "날씨 아이콘 번호 (https://developer.accuweather.com/weather-icons)")
    @JsonProperty("WeatherIcon")
    private int weatherIcon;

    @Schema(description = "날씨 아이콘 이름")
    @JsonProperty("IconPhrase")
    private String iconPhrase;

    @Schema(description = "온도")
    @JsonProperty("Temperature")
    private float temperature;

    @Schema(description = "강수 확률")
    @JsonProperty("PrecipitationProbability")
    private int precipitationProbability;

    @Schema(description = "시간 별 날씨")
    @JsonProperty("HourlyForecast")
    private List<HourlyForecastDto> hourlyForecast;
}
