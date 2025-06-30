package com.nitheesh.review_service.repository;

import com.nitheesh.review_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,String> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.hotelId = :hotelId")
    float findAverageRatingByHotelId(String hotelId);



}
