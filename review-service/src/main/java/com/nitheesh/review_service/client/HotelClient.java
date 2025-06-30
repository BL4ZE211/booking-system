package com.nitheesh.review_service.client;

import com.nitheesh.review_service.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "hotel-service")
public interface HotelClient {

   @PutMapping("/hotels/{hotelId}/{rating}")
    public Hotel updateRating(@PathVariable("hotelId")String id,@PathVariable("rating") float rating);


}
