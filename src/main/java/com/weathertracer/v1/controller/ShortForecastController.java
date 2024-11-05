package com.weathertracer.v1.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathertracer.v1.dto.ShortForecast;
import com.weathertracer.v1.util.LambertProjection;
import com.weathertracer.v1.vo.Gps;
import com.weathertracer.v1.vo.Location;

@RequestMapping("/forecast/short")
@RestController
public class ShortForecastController {

    @Value("${service.key}")
    private String serviceKey;

    @GetMapping({ "", "/" })
    public ResponseEntity<ShortForecast> getShortForecast() {
        Gps gps = new Gps(37.29518064456937, 127.04065801381438);
        Location location = LambertProjection.ConvertGpsToLocation(gps);
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        RestTemplate restTemplate = new RestTemplate();
        ShortForecast shortForecast = new ShortForecast();
        shortForecast.setMeasurementTime(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // #region 위치 정보 조회
        try {
            String apiUrl = "https://nominatim.openstreetmap.org/reverse";
            String format = "json";
            ResponseEntity<String> regionInfoResponse = restTemplate
                    .getForEntity(apiUrl + "?lat=" + gps.lat() + "&lon=" + gps.lon() + "&format=" + format,
                            String.class);

            if (regionInfoResponse.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode addressNode = objectMapper.readTree(regionInfoResponse.getBody()).path("address");
            String address = String.join(" ", addressNode.path("city").asText(), addressNode.path("borough").asText(),
                    addressNode.path("quarter").asText());
            shortForecast.setAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // #endregion

        try {
            String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst" + "?serviceKey="
                    + serviceKey
                    + "&pageNo=1&numOfRows=1000&dataType=JSON&base_date="
                    + currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    + "&base_time=" + currentDateTime.format(DateTimeFormatter.ofPattern("HHmm"))
                    + "&nx=" + location.x() + "&ny=" + location.y();
            ResponseEntity<String> regionInfoResponse = restTemplate.getForEntity(apiUrl, String.class);

            if (regionInfoResponse.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(regionInfoResponse.getBody()).path("response");

            JsonNode itemsNode = rootNode.path("body").path("items").path("item");
            for (JsonNode itemNode : itemsNode) {
                String category = itemNode.path("category").asText();
                if (category.equals("T1H")) {
                    shortForecast.setTemperature(itemNode.path("obsrValue").asDouble());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        shortForecast.setSky("맑음");
        shortForecast.setPrecipitationProbability(0);

        return new ResponseEntity<ShortForecast>(shortForecast, HttpStatus.OK);

    }
}
