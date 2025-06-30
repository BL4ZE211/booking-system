package com.nitheesh.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String userId;
    private String hotelId;
    private String checkIn;
    private String checkOut;
}
