package com.example.Task_2_Third_party_API_Integration_Using_JSON.controller;

import com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity.Weather;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.paylaods.CurrentDto;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.paylaods.RequestDto;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.paylaods.WeatherDto;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.service.WeatherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
//        String weatherstring="Day: "+weather.getCurrent().getIs_day()+
//                "\nPressure: "+weather.getCurrent().getPressure()+
//                "\nTemperature: "+weather.getCurrent().getTemperature();
        WeatherDto weatherDto=new WeatherDto();
        CurrentDto currentDto=new CurrentDto();
        RequestDto requestDto=new RequestDto();

        currentDto.setTemperature(weather.getCurrent().getTemperature());
        currentDto.setPressure(weather.getCurrent().getPressure());
        requestDto.setQuery(weather.getRequest().getQuery());
        weatherDto.setCurrent(currentDto);
        weatherDto.setRequest(requestDto);

        return new ResponseEntity<>(weatherDto, HttpStatus.OK);
    }

}
