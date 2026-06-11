package com.booking.shyamoli.controller;

import com.booking.shyamoli.entity.Place;
import com.booking.shyamoli.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{city}")
    public List<Place> getPlaces(
            @PathVariable String city
    ){
        return placeService.getPlacesByCity(city);
    }
}
