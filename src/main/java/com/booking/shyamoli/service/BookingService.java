package com.booking.shyamoli.service;

import com.booking.shyamoli.dto.BookingRequest;
import com.booking.shyamoli.dto.BookingResponse;
import com.booking.shyamoli.dto.PassengerDto;
import com.booking.shyamoli.entity.Booking;
import com.booking.shyamoli.entity.Trip;
import com.booking.shyamoli.enums.BookingStatus;
import com.booking.shyamoli.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final TripService tripService;

    private final SeatValidationService
            seatValidationService;

    public BookingResponse bookTicket(
            BookingRequest request
    ){

        Trip trip =
                tripService.getTrip(
                        request.getTripId()
                );

        seatValidationService
                .validateSeats(
                        trip.getId(),
                        request.getPassengers()
                );

        List<Booking> bookings =
                request.getPassengers()
                        .stream()
                        .map(passenger ->
                                createBooking(
                                        passenger,
                                        request,
                                        trip
                                )
                        )
                        .toList();

        bookingRepository.saveAll(
                bookings
        );

        return new BookingResponse(
                bookings.get(0).getId(),
                "CONFIRMED",
                bookings.size()
                        + " seat(s) booked successfully"
        );
    }

    private Booking createBooking(
            PassengerDto passenger,
            BookingRequest request,
            Trip trip
    ){

        return Booking.builder()
                .passengerName(
                        passenger.getName()
                )
                .phoneNumber(
                        passenger.getPhoneNumber()
                )
                .seatNo(
                        passenger.getSeatNo()
                )
                .gender(
                        passenger.getGender()
                )
                .pickupPlace(
                        request.getPickupPlace()
                )
                .dropPlace(
                        request.getDropPlace()
                )
                .bookingStatus(
                        BookingStatus.CONFIRMED
                )
                .trip(trip)
                .build();
    }

    public List<Booking> getBookings(
            Long tripId
    ){

        return bookingRepository
                .findByTripId(tripId);
    }
    public Booking getBooking(Long bookingId){

        return bookingRepository
                .findById(bookingId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Booking not found"
                        )
                );
    }

    public List<Booking> bookingHistory(
            String phoneNumber
    ){

        return bookingRepository
                .findByPhoneNumber(
                        phoneNumber
                );
    }

    public String cancelBooking(
            Long bookingId
    ){

        Booking booking =
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Booking not found"
                                        )
                        );

        booking.setBookingStatus(
                BookingStatus.CANCELLED
        );

        bookingRepository.save(booking);

        return "Booking cancelled successfully";
    }
}
