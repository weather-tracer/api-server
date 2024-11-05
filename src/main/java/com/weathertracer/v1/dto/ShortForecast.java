package com.weathertracer.v1.dto;

import lombok.Data;

/**
 * 단기 예보
 */
@Data
public class ShortForecast {
    private String measurementTime;
    private String address;
    private double temperature; // 초단기 실황에서 가져옴
    private String sky; // 초단기 예보에서 가져옴
    private double precipitationProbability; // 단기 예보에서 가져옴
}