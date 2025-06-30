package com.nitheesh.booking_service.repository;

import com.nitheesh.booking_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,String> {

    boolean existsByHotelIdAndUserId(String hotelId, String userId);
}
