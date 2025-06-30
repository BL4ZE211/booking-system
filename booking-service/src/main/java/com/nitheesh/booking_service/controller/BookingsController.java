package com.nitheesh.booking_service.controller;

import com.nitheesh.booking_service.dto.BookingRequest;
import com.nitheesh.booking_service.dto.BookingResponse;
import com.nitheesh.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingsController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest){
        return bookingService.createBooking(bookingRequest);
    }

    @GetMapping
    public List<BookingResponse> getAllBookings(){
        return bookingService.getAllbookings();
    }

    @GetMapping("/{hotelId}/{userId}")
    public boolean checkBooking(@PathVariable String hotelId, @PathVariable String userId){
        return bookingService.checkBooking(hotelId,userId);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingBuId(@PathVariable String id){
        return bookingService.getBookingBuId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable String id){
        bookingService.deleteBooking(id);
    }

    @PutMapping("{id}")
    public BookingResponse updateBookingDetails(@RequestBody BookingRequest bookingRequest,@PathVariable String  id){
        return bookingService.updateBooking(bookingRequest,id);
    }
}
