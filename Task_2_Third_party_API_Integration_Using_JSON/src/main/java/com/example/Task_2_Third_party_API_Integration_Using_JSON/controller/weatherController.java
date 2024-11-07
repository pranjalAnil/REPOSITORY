package com.example.Task_2_Third_party_API_Integration_Using_JSON.controller;

import com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity.Weather;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class weatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/data/{city}")
    public ResponseEntity<?> getWeatherDetails(@PathVariable String city){
        Weather weather=weatherService.weatherData(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

}
