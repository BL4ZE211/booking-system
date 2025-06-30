package com.nitheesh.review_service.client;

import com.nitheesh.review_service.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hotel-service")
public interface HotelClient {

    @GetMapping("/hotels/{id}")
    public Hotel getHotel(@PathVariable("id") String id);
}
