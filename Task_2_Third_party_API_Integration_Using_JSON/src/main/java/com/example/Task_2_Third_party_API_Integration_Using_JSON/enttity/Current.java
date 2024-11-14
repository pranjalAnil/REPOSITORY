package com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity;
import lombok.Data;

@Data
public class Current {
    private String observation_time;
    private int temperature;
    private int weather_code;
    private int wind_speed;
    private int wind_degree;
    private String wind_dir;
    private int pressure;
    private int precip;
    private int humidity;
    private int cloudcover;
    private int feelslike;
    private int uv_index;
    private int visibility;
    private String is_day;
}
