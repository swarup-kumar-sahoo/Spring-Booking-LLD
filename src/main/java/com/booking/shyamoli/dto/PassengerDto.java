package com.booking.shyamoli.dto;

import com.booking.shyamoli.enums.Gender;
import lombok.Data;

@Data
public class PassengerDto {

    private String name;

    private String phoneNumber;

    private Gender gender;

    private String seatNo;
}
