package com.booking.shyamoli.service;

import com.booking.shyamoli.dto.CreateTripRequest;
import com.booking.shyamoli.dto.TripResponse;
import com.booking.shyamoli.entity.Trip;
import com.booking.shyamoli.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public Trip createTrip(
            CreateTripRequest request
    ){

        Trip trip = Trip.builder()
                .fromCity(
                        request.getFromCity()
                )
                .toCity(
                        request.getToCity()
                )
                .date(
                        LocalDate.parse(
                                request.getDate()
                        )
                )
                .time(
                        LocalTime.parse(
                                request.getTime()
                        )
                )
                .build();

        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips(){
        return tripRepository.findAll();
    }

    public Trip getTrip(
            Long tripId
    ){

        return tripRepository.findById(
                tripId
        ).orElseThrow(
                () -> new RuntimeException(
                        "Trip not found"
                )
        );
    }

    public List<Trip> searchTrips(
            String from,
            String to
    ){

        return tripRepository
                .findByFromCityAndToCity(
                        from,
                        to
                );
    }
}
