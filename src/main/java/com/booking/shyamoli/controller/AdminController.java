package com.booking.shyamoli.controller;

import com.booking.shyamoli.dto.CreateTripRequest;
import com.booking.shyamoli.entity.Trip;
import com.booking.shyamoli.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TripService tripService;

    @PostMapping("/trips")
    public ResponseEntity<Trip> createTrip(
            @RequestBody CreateTripRequest request
    ){
        return ResponseEntity.ok(
                tripService.createTrip(request)
        );
    }
}
