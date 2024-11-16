package com.weathertracer.v1.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathertracer.v1.dto.CurrentForecastDto;
import com.weathertracer.v1.service.ForecastService;
import com.weathertracer.v1.service.LocationService;
import com.weathertracer.v1.vo.Forecast;
import com.weathertracer.v1.vo.Location;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/current")
@RestController
public class ForecastController {
    @Autowired
    private ForecastService forecastService;

    @Tag(name = "현재 날씨 조회", description = "위경도를 입력받아 해당 위치의 현재 날씨와 예보를 조회합니다.")
    @GetMapping()
    public ResponseEntity<CurrentForecastDto> getCurrentForecast(
            @Schema(description = "위도", requiredMode = RequiredMode.REQUIRED, example = "37.26568758921002") @RequestParam(value = "lat", required = true) BigDecimal lat,
            @Schema(description = "경도", requiredMode = RequiredMode.REQUIRED, example = "127.0000648245165") @RequestParam(value = "lon", required = true) BigDecimal lon) {
        return new ResponseEntity<>(this.forecastService.getCurrent(lat, lon), HttpStatus.OK);
    }
}
