package com.booking.shyamoli.controller;

import com.booking.shyamoli.entity.Trip;
import com.booking.shyamoli.entity.Booking;
import com.booking.shyamoli.service.BookingService;
import com.booking.shyamoli.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(){
        return ResponseEntity.ok(
                tripService.getAllTrips()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(
            @RequestParam String from,
            @RequestParam String to
    ){

        return ResponseEntity.ok(
                tripService.searchTrips(from,to)
        );
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTrip(
            @PathVariable Long tripId
    ){

        return ResponseEntity.ok(
                tripService.getTrip(tripId)
        );
    }

    @GetMapping("/{tripId}/seats")
    public ResponseEntity<Map<String,Object>>
    getSeatLayout(
            @PathVariable Long tripId
    ){

        List<String> upperSeats = List.of(
                "U1","U2","U3",
                "U4","U5","U6",
                "U7","U8","U9",
                "U10","U11","U12",
                "U13","U14","U15",
                "U16","U17","U18",
                "U19","U20","U21"
        );

        List<String> lowerSeats = List.of(
                "L1","L2","L3",
                "L4","L5","L6",
                "L7","L8","L9",
                "L10","L11","L12",
                "L13","L14","L15",
                "L16","L17","L18",
                "L19","L20","L21"
        );

        List<String> bookedSeats =
                bookingService
                        .getBookings(tripId)
                        .stream()
                        .map(booking ->
                                booking.getSeatNo()
                        )
                        .toList();

        Map<String,Object> response =
                new HashMap<>();

        response.put("upperSeats",
                upperSeats);

        response.put("lowerSeats",
                lowerSeats);

        response.put("bookedSeats",
                bookedSeats);

        return ResponseEntity.ok(
                response
        );
    }
    @GetMapping("/{tripId}/available-seats")
    public ResponseEntity<Map<String,Object>> getAvailableSeats(
            @PathVariable Long tripId
    ){

        List<String> allSeats = new ArrayList<>();

        for(int i=1;i<=21;i++){
            allSeats.add("U"+i);
            allSeats.add("L"+i);
        }

        List<String> bookedSeats =
                bookingService.getBookings(tripId)
                        .stream()
                        .map(Booking::getSeatNo)
                        .toList();

        allSeats.removeAll(bookedSeats);

        return ResponseEntity.ok(
                Map.of(
                        "availableSeats",
                        allSeats
                )
        );
    }

    @GetMapping("/{tripId}/passengers")
    public ResponseEntity<List<Map<String,Object>>> getPassengers(
            @PathVariable Long tripId
    ){

        List<Map<String,Object>> passengers =
                bookingService.getBookings(tripId)
                        .stream()
                        .map(b -> {
                            Map<String,Object> passenger =
                                    new HashMap<>();

                            passenger.put(
                                    "name",
                                    b.getPassengerName()
                            );

                            passenger.put(
                                    "seatNo",
                                    b.getSeatNo()
                            );

                            passenger.put(
                                    "phoneNumber",
                                    b.getPhoneNumber()
                            );

                            return passenger;
                        })
                        .toList();

        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{tripId}/summary")
    public ResponseEntity<Map<String,Object>> getSummary(
            @PathVariable Long tripId
    ){

        int totalSeats = 42;

        int bookedSeats =
                bookingService.getBookings(tripId)
                        .size();

        int availableSeats =
                totalSeats - bookedSeats;

        return ResponseEntity.ok(
                Map.of(
                        "tripId", tripId,
                        "totalSeats", totalSeats,
                        "bookedSeats", bookedSeats,
                        "availableSeats", availableSeats
                )
        );
    }

}
