package com.example.Task_2_Third_party_API_Integration_Using_JSON.paylaods;

import com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity.Current;
import com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity.Request;
import lombok.Data;

@Data
public class WeatherDto {
    private CurrentDto current;
    private RequestDto request;
}
