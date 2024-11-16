package com.weathertracer.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "시간 별 날씨 정보")
@Data
public class HourlyForecastDto {
    @Schema(description = "시간 (UTC 초)")
    @JsonProperty("EpochSecond")
    private long epochSecond;

    @Schema(description = "온도")
    @JsonProperty("Temperature")
    private float temperature;

    @Schema(description = "날씨 아이콘")
    @JsonProperty("WeatherIcon")
    private int weatherIcon;

    @Schema(description = "강수 확률")
    @JsonProperty("PrecipitationProbability")
    private int precipitationProbability;
}
