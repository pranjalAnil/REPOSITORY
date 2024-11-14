package com.example.Task_2_Third_party_API_Integration_Using_JSON.enttity;
import lombok.Data;

@Data
public class Request {
    private String type;
    private String query;
    private String language;
    private String unit;
}
