package com.booking.shyamoli.repository;

import com.booking.shyamoli.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository
        extends JpaRepository<Place, Long> {

    List<Place> findByCity(String city);
}