package com.example.Task_3_Thirdparty_API_Integration_XML.Controller;

import com.example.Task_3_Thirdparty_API_Integration_XML.entity.Weather;
import com.example.Task_3_Thirdparty_API_Integration_XML.services.WeatherService;
import com.example.Task_3_Thirdparty_API_Integration_XML.services.implementation.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/data/{city}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getWeatherDetails(@PathVariable String city) {
        Weather weather = weatherService.weatherData(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }


}
