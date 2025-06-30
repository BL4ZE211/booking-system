package com.nitheesh.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelResponseDto {
    private String id;
    private String name;
    private float rating;
}
