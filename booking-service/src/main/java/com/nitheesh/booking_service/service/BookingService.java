package com.nitheesh.booking_service.service;


import com.nitheesh.booking_service.BookingServiceApplication;
import com.nitheesh.booking_service.client.HotelClient;
import com.nitheesh.booking_service.client.UserClient;
import com.nitheesh.booking_service.dto.BookingRequest;
import com.nitheesh.booking_service.dto.BookingResponse;
import com.nitheesh.booking_service.dto.Hotel;
import com.nitheesh.booking_service.dto.User;
import com.nitheesh.booking_service.entity.Booking;
import com.nitheesh.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private UserClient userClient;

    public BookingResponse mapToDto(Booking booking){
        BookingResponse bookingResponse =  new BookingResponse();
        bookingResponse.setBookingId(booking.getId());
        bookingResponse.setMessage("CheckIn date : "+booking.getCheckInDate()+" check out date : "+booking.getCheckOutDate());

        return bookingResponse;
    }


    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        User user = userClient.getUserById(bookingRequest.getUserId());
        if(user==null){
            throw new RuntimeException("No user found");
        }
        Hotel hotel = hotelClient.getHotel(bookingRequest.getHotelId());
        if(hotel==null){
            throw new RuntimeException("No hotel found");
        }
        booking.setUserId(user.getId());
        booking.setHotelId(hotel.getId());
        booking.setCheckInDate( LocalDate.parse(bookingRequest.getCheckIn()));
        booking.setCheckOutDate(LocalDate.parse(bookingRequest.getCheckOut()));

        bookingRepository.save(booking);

        return mapToDto(booking);

    }

    public List<BookingResponse> getAllbookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        ArrayList<BookingResponse> bookingResponses = new ArrayList<>();
        for(Booking booking : allBookings){
            bookingResponses.add(mapToDto(booking));
        }

        return bookingResponses;
    }

    public BookingResponse getBookingBuId(String id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No booking found with id : "+ id));

        return mapToDto(booking);
    }

    public void deleteBooking(String id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No booking found with id : "+ id));

        bookingRepository.deleteById(booking.getId());
    }

    public BookingResponse updateBooking(BookingRequest bookingRequest,String id) {
        Booking existingBooking = bookingRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No booking found with id : "+ id));

        if(bookingRequest.getUserId()!=null){
            existingBooking.setUserId(bookingRequest.getUserId());
        }
        if(bookingRequest.getHotelId()!=null){
            existingBooking.setHotelId(bookingRequest.getHotelId());
        }
        if(bookingRequest.getCheckIn()!=null){
            existingBooking.setCheckInDate(LocalDate.parse(bookingRequest.getCheckIn()));
        }
        if(bookingRequest.getUserId()!=null){
            existingBooking.setCheckOutDate(LocalDate.parse(bookingRequest.getCheckOut()));
        }

        bookingRepository.save(existingBooking);

        return mapToDto(existingBooking);
    }
}
