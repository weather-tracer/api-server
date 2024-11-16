package com.weathertracer.v1.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.weathertracer.v1.constants.Constants;
import com.weathertracer.v1.dto.CurrentForecastDto;
import com.weathertracer.v1.dto.HourlyForecastDto;
import com.weathertracer.v1.service.ForecastService;
import com.weathertracer.v1.service.LocationService;
import com.weathertracer.v1.vo.Forecast;
import com.weathertracer.v1.vo.Location;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Autowired
    private WebClient webClient;

    @Value("${accuweather.apikey}")
    private String apikey;

    @Autowired
    private LocationService locationService;

    @Override
    public CurrentForecastDto getCurrent(BigDecimal lat, BigDecimal lon) {
        Location location = this.locationService.getLocation(lat, lon);

        List<Forecast> forecasts = this.get12Hour(location.getKey());
        Forecast firstForecast = forecasts.get(0);

        List<HourlyForecastDto> hourlyForecasts = new ArrayList<HourlyForecastDto>();
        for (Forecast forecast : forecasts) {
            HourlyForecastDto hourlyForecast = new HourlyForecastDto();
            hourlyForecast.setEpochSecond(forecast.getEpochDateTime());
            hourlyForecast.setTemperature(forecast.getTemperature().getValue());
            hourlyForecast.setWeatherIcon(forecast.getWeatherIcon());
            hourlyForecast.setPrecipitationProbability(forecast.getPrecipitationProbability());
            hourlyForecasts.addLast(hourlyForecast);
        }

        CurrentForecastDto curForecastDto = new CurrentForecastDto();
        curForecastDto.setAddress(location.getParentCity().getLocalizedName() + " " + location.getLocalizedName());
        curForecastDto.setEpochSecond(Instant.now().getEpochSecond());
        curForecastDto.setWeatherIcon(firstForecast.getWeatherIcon());
        curForecastDto.setIconPhrase(firstForecast.getIconPhrase());
        curForecastDto.setTemperature(firstForecast.getTemperature().getValue());
        curForecastDto.setPrecipitationProbability(firstForecast.getPrecipitationProbability());
        curForecastDto.setHourlyForecast(hourlyForecasts);
        return curForecastDto;
    }

    @Override
    public List<Forecast> get12Hour(String key) {
        String path = "/forecasts/v1/hourly/12hour/" + key;

        List<Forecast> forecasts = webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(Constants.BASE_URL + path)
                        .queryParam("apikey", apikey)
                        .queryParam("details", true)
                        .queryParam("metric", true)
                        .queryParam("language", Constants.LANGUAGE_KO_KR)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Forecast>>() {
                })
                .timeout(Duration.ofSeconds(10))
                .block();

        return forecasts;
    }
}
