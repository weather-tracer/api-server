package com.weathertracer.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/forecast/short")
@RestController
public class ShortForecastController {
    @GetMapping({ "", "/" })
    public ResponseEntity<Object> getMemoList() {


        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
