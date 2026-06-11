package com.booking.shyamoli.repository;

import com.booking.shyamoli.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    List<Booking> findByTripId(Long tripId);

    boolean existsByTripIdAndSeatNo(
            Long tripId,
            String seatNo
    );

    List<Booking> findByTripIdAndSeatNoIn(
            Long tripId,
            List<String> seatNos
    );
}
