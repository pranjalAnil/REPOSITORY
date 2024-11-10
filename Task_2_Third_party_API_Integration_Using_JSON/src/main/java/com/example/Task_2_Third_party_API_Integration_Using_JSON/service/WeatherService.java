package com.example.Task_2_Third_party_API_Integration_Using_JSON.service;

import com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity.Weather;

public interface WeatherService {
    public Weather weatherData(String city);
}
