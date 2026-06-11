package com.booking.shyamoli.service;

import com.booking.shyamoli.entity.Place;
import com.booking.shyamoli.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getPlacesByCity(
            String city
    ){
        return placeRepository.findByCity(city);
    }
}
