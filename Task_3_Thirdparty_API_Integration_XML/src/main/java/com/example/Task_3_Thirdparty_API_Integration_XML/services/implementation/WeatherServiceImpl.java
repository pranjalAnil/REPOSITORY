package com.example.Task_3_Thirdparty_API_Integration_XML.services.implementation;

import com.example.Task_3_Thirdparty_API_Integration_XML.entity.Weather;
import com.example.Task_3_Thirdparty_API_Integration_XML.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String apiKey="423d8d361263573a40ac53a09fa9090e";

    @Autowired
    RestTemplate restTemplate;
    public Weather weatherData(String city){
        String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY&format=xml";
        String finalApi= API.replace("API_KEY",apiKey).replace("CITY",city);
        ResponseEntity<Weather> weatherResponseEntity=
                restTemplate.exchange(finalApi, HttpMethod.GET,null, Weather.class);

        return weatherResponseEntity.getBody();

    }
}
