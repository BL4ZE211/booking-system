package com.nitheesh.review_service.entity;

import com.nitheesh.review_service.dto.Hotel;
import com.nitheesh.review_service.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String userId;


    private String hotelId;

    private float rating;
    private String comment;

    @CreationTimestamp
    private Timestamp createdAt;
}
