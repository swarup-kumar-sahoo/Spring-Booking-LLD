package com.booking.shyamoli.controller;

import com.booking.shyamoli.dto.BookingRequest;
import com.booking.shyamoli.dto.BookingResponse;
import com.booking.shyamoli.entity.Booking;
import com.booking.shyamoli.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse>
    bookTicket(
            @RequestBody BookingRequest request
    ){

        return ResponseEntity.ok(
                bookingService.bookTicket(
                        request
                )
        );
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Booking>>
    getBookings(
            @PathVariable Long tripId
    ){

        return ResponseEntity.ok(
                bookingService
                        .getBookings(tripId)
        );
    }
}
