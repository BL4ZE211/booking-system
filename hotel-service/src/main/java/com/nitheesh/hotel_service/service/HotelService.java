package com.nitheesh.hotel_service.service;

import com.nitheesh.hotel_service.dto.HotelResponseDto;
import com.nitheesh.hotel_service.dto.HoterlRequestDto;
import com.nitheesh.hotel_service.entity.Hotel;
import com.nitheesh.hotel_service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    public HotelResponseDto createHotel(HoterlRequestDto hoterlRequestDto) {
        Hotel hotel = new Hotel();
        hotel.setRating(0);
        hotel.setName(hoterlRequestDto.getName());
        hotel.setLocation(hoterlRequestDto.getLocation());

        hotelRepository.save(hotel);

        return mapToDto(hotel);

    }

    private HotelResponseDto mapToDto(Hotel hotel) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto();
        hotelResponseDto.setId(hotel.getId());
        hotelResponseDto.setName(hotel.getName());
        hotelResponseDto.setRating(hotel.getRating());

        return hotelResponseDto;
    }

    public List<HotelResponseDto> getALlHotels() {
        List<Hotel> allHotels = hotelRepository.findAll();
        ArrayList<HotelResponseDto> hotelResponse = new ArrayList<>();

        for(Hotel hotel:allHotels){
            hotelResponse.add(mapToDto(hotel));
        }

        return hotelResponse;

    }

    public HotelResponseDto gtHotelById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No hotel found"));

        return mapToDto(hotel);
    }

    public void deleteHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No hotel found"));

        hotelRepository.deleteById(id);
    }

    public HotelResponseDto updateDetails(String id, HoterlRequestDto hoterlRequestDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No user found "));

        if(hoterlRequestDto.getName()!=null){
            hotel.setName(hoterlRequestDto.getName());
        }
        if(hoterlRequestDto.getLocation()!=null){
            hotel.setLocation(hoterlRequestDto.getLocation());
        }

        hotelRepository.save(hotel);

        return mapToDto(hotel);
    }
}
