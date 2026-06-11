package com.booking.shyamoli.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    @NotNull
    private Long tripId;
    @NotBlank
    private String pickupPlace;
    @NotBlank
    private String dropPlace;

    private List<PassengerDto> passengers;
}
