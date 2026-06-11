package com.booking.shyamoli.controller;

import com.booking.shyamoli.dto.BookingRequest;
import com.booking.shyamoli.dto.BookingResponse;
import com.booking.shyamoli.entity.Booking;
import com.booking.shyamoli.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(
            @PathVariable Long bookingId
    ){

        return ResponseEntity.ok(
                bookingService.getBooking(
                        bookingId
                )
        );
    }

    @GetMapping("/history/{phoneNumber}")
    public ResponseEntity<List<Booking>>
    getBookingHistory(
            @PathVariable String phoneNumber
    ){

        return ResponseEntity.ok(
                bookingService.bookingHistory(
                        phoneNumber
                )
        );
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Map<String,String>>
    cancelBooking(
            @PathVariable Long bookingId
    ){

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        bookingService.cancelBooking(
                                bookingId
                        )
                )
        );
    }
}
