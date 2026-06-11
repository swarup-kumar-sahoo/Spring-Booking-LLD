package com.booking.shyamoli.dto;

import lombok.Data;

@Data
public class CreateTripRequest {

    private String fromCity;

    private String toCity;

    private String date;

    private String time;
}