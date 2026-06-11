package com.booking.shyamoli.service;

import com.booking.shyamoli.dto.PassengerDto;
import com.booking.shyamoli.entity.Booking;
import com.booking.shyamoli.enums.Gender;
import com.booking.shyamoli.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SeatValidationService {

    private final BookingRepository bookingRepository;

    private static final Map<String, List<String>> ADJACENT_SEATS =
            new HashMap<>();

    static {

        addPair("U2","U3");
        addPair("U5","U6");
        addPair("U8","U9");
        addPair("U11","U12");
        addPair("U14","U15");
        addPair("U17","U18");
        addPair("U20","U21");

        addPair("L2","L3");
        addPair("L5","L6");
        addPair("L8","L9");
        addPair("L11","L12");
        addPair("L14","L15");
        addPair("L17","L18");
        addPair("L20","L21");
    }

    private static void addPair(
            String seat1,
            String seat2
    ){
        ADJACENT_SEATS.put(
                seat1,
                List.of(seat2)
        );

        ADJACENT_SEATS.put(
                seat2,
                List.of(seat1)
        );
    }

    public void validateSeats(
            Long tripId,
            List<PassengerDto> passengers
    ){

        Set<String> requestedSeats =
                new HashSet<>();

        for(PassengerDto passenger : passengers){

            String seatNo =
                    passenger.getSeatNo();

            if(!requestedSeats.add(seatNo)){
                throw new RuntimeException(
                        "Duplicate seat selected : "
                                + seatNo
                );
            }

            if(bookingRepository
                    .existsByTripIdAndSeatNo(
                            tripId,
                            seatNo
                    )){

                throw new RuntimeException(
                        seatNo +
                                " already booked"
                );
            }
        }

        validateGenderRule(
                tripId,
                passengers
        );
    }

    private void validateGenderRule(
            Long tripId,
            List<PassengerDto> passengers
    ){

        Set<String> currentBookingSeats =
                new HashSet<>();

        for(PassengerDto p : passengers){
            currentBookingSeats.add(
                    p.getSeatNo()
            );
        }

        List<Booking> existingBookings =
                bookingRepository
                        .findByTripId(tripId);

        for(PassengerDto passenger
                : passengers){

            String seat =
                    passenger.getSeatNo();

            Gender gender =
                    passenger.getGender();

            List<String> adjacentSeats =
                    ADJACENT_SEATS
                            .getOrDefault(
                                    seat,
                                    Collections.emptyList()
                            );

            for(Booking booking
                    : existingBookings){

                if(adjacentSeats.contains(
                        booking.getSeatNo()
                )){

                    if(currentBookingSeats.contains(
                            booking.getSeatNo()
                    )){
                        continue;
                    }

                    if(!booking.getGender()
                            .equals(gender)){

                        throw new RuntimeException(
                                "Cannot book "
                                        + seat
                                        + " because adjacent seat "
                                        + booking.getSeatNo()
                                        + " is occupied by "
                                        + booking.getGender()
                        );
                    }
                }
            }
        }
    }
}
