package com.weathertracer.v1.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.weathertracer.v1.vo.Location;

@Service
public interface LocationService {
    Location getLocation(BigDecimal lat, BigDecimal lon);
}
