package com.booking.shyamoli.config;

import com.booking.shyamoli.entity.Place;
import com.booking.shyamoli.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PlaceRepository placeRepository;

    @Override
    public void run(String... args) {

        if(placeRepository.count() > 0){
            return;
        }

        List<Place> places = List.of(

                Place.builder()
                        .city("Hyderabad")
                        .placeName("Ramoji Film City")
                        .build(),

                Place.builder()
                        .city("Hyderabad")
                        .placeName("Dilsukhnagar")
                        .build(),

                Place.builder()
                        .city("Hyderabad")
                        .placeName("Ameerpet")
                        .build(),

                Place.builder()
                        .city("Hyderabad")
                        .placeName("Kukatpally")
                        .build(),

                Place.builder()
                        .city("Bhubaneswar")
                        .placeName("Baramunda")
                        .build(),

                Place.builder()
                        .city("Bhubaneswar")
                        .placeName("Khandagiri")
                        .build(),

                Place.builder()
                        .city("Bhubaneswar")
                        .placeName("Khordha")
                        .build(),

                Place.builder()
                        .city("Bhubaneswar")
                        .placeName("Berhampur")
                        .build()
        );

        placeRepository.saveAll(places);
    }
}
