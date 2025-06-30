package com.nitheesh.booking_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nitheesh.booking_service.dto.Hotel;
import com.nitheesh.booking_service.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String userId;


    private String hotelId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    @CreationTimestamp
    private Timestamp bookedAt;
}
