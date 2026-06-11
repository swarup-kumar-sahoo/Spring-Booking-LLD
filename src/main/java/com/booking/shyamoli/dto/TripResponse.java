package com.booking.shyamoli.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripResponse {

    private Long id;

    private String fromCity;

    private String toCity;

    private String date;

    private String time;
}