package com.nitheesh.review_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/booking/{hotelId}/{userId}")
    public boolean checkBooking(@PathVariable("hotelId") String hotelId,@PathVariable("userId") String id);
}
