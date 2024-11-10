package com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity;

import lombok.Data;

@Data
public class Current {
    public String observation_time;
    public int temperature;
    public int weather_code;
    public int wind_speed;
    public int wind_degree;
    public String wind_dir;
    public int pressure;
    public int precip;
    public int humidity;
    public int cloudcover;
    public int feelslike;
    public int uv_index;
    public int visibility;
    public String is_day;
}
