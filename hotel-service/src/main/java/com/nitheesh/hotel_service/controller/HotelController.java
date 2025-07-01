package com.nitheesh.hotel_service.controller;

import com.nitheesh.hotel_service.dto.HotelResponseDto;
import com.nitheesh.hotel_service.dto.HoterlRequestDto;
import com.nitheesh.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public HotelResponseDto createHote(@RequestBody HoterlRequestDto hoterlRequestDto){
        return hotelService.createHotel(hoterlRequestDto);
    }

    @GetMapping
    public List<HotelResponseDto> getAllHotels(){
        return hotelService.getALlHotels();
    }

    @GetMapping("/{id}")
    public HotelResponseDto getHotelById(@PathVariable String id){
        return  hotelService.gtHotelById(id);
    }

    @DeleteMapping("/{id}")
    public void deletHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
    }

    @PutMapping("/{id}")
    public HotelResponseDto updateHotelDetails(@PathVariable String  id,@RequestBody HoterlRequestDto hoterlRequestDto){
        return hotelService.updateDetails(id,hoterlRequestDto);
    }

    @PutMapping("/{hotelId}/{rating}")
    public HotelResponseDto updateRating(@PathVariable String hotelId,@PathVariable float rating){
        return hotelService.updateRating(hotelId,rating);
    }
}
