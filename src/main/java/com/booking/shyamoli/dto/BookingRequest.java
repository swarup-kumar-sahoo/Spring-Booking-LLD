package com.booking.shyamoli.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    private Long tripId;

    private String pickupPlace;

    private String dropPlace;

    private List<PassengerDto> passengers;
}
