package com.weathertracer.v1.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weathertracer.v1.dto.CurrentForecastDto;
import com.weathertracer.v1.vo.Forecast;

@Service
public interface ForecastService {
    CurrentForecastDto getCurrent(BigDecimal lat, BigDecimal lon);

    List<Forecast> get12Hour(String key);
}
