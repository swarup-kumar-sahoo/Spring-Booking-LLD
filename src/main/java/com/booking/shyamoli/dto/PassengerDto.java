package com.booking.shyamoli.dto;

import com.booking.shyamoli.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PassengerDto {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @NotBlank
    private String seatNo;
}
