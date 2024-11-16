package com.weathertracer.v1.service.impl;

import java.math.BigDecimal;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.weathertracer.v1.constants.Constants;
import com.weathertracer.v1.service.LocationService;
import com.weathertracer.v1.vo.Location;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private WebClient webClient;

    @Value("${accuweather.apikey}")
    private String apikey;

    @Override
    public Location getLocation(BigDecimal lat, BigDecimal lon) {
        String path = "/locations/v1/cities/geoposition/search";

        String query = String.format("%s,%s", lat, lon);

        Location location = webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(Constants.BASE_URL + path)
                        .queryParam("apikey", apikey)
                        .queryParam("q", query)
                        .queryParam("language", Constants.LANGUAGE_KO_KR)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(Location.class)
                .timeout(Duration.ofSeconds(10))
                .block();

        return location;
    }
}
