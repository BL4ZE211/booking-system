package com.nitheesh.review_service.service;

import com.nitheesh.review_service.client.BookingClient;
import com.nitheesh.review_service.client.HotelClient;
import com.nitheesh.review_service.dto.Hotel;
import com.nitheesh.review_service.dto.ReviewRequest;
import com.nitheesh.review_service.dto.ReviewResponse;
import com.nitheesh.review_service.dto.User;
import com.nitheesh.review_service.entity.Review;
import com.nitheesh.review_service.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private BookingClient bookingClient;



    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        if(bookingClient.checkBooking(reviewRequest.getHotelId(),reviewRequest.getUserId())){
            Review review = new Review();
            review.setComment(reviewRequest.getComment());
            review.setUserId(reviewRequest.getUserId());
            review.setHotelId(reviewRequest.getHotelId());
            review.setRating(reviewRequest.getRating());

            repository.save(review);
            updateRating(reviewRequest.getHotelId());
            return  mapToDto(review);
        }else{
            throw new RuntimeException("No booking found");
        }
    }

    private ReviewResponse mapToDto(Review review) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setId(review.getId());
        reviewResponse.setComment(review.getComment());
        reviewResponse.setRating(review.getRating());
        reviewResponse.setUserId(review.getUserId());
        reviewResponse.setHotelId(review.getHotelId());

        return reviewResponse;
    }


    public List<ReviewResponse> getAllReviews() {
        List<Review> allReviews = repository.findAll();
        ArrayList<ReviewResponse> reviewResponses = new ArrayList<>();
        for(Review review:allReviews){
            reviewResponses.add(mapToDto(review));
        }
        return reviewResponses;
    }

    public ReviewResponse getReviewById(String id) {
        Review review = repository.findById(id).orElseThrow(()-> new RuntimeException("No review found "));
        return mapToDto(review);
    }

    public void deleteById(String id) {
        Review review = repository.findById(id).orElseThrow(()-> new RuntimeException("No review found "));
        repository.deleteById(review.getId());
    }

    public ReviewResponse updateReviw(String id, ReviewRequest reviewRequest) {
        Review existingReview = repository.findById(id).orElseThrow(()-> new RuntimeException("No review found "));

        if (reviewRequest.getUserId()!= null) {
            existingReview.setUserId(reviewRequest.getUserId());
        }

        if (reviewRequest.getHotelId()!= null) {
            existingReview.setHotelId(reviewRequest.getHotelId());
        }

        if (reviewRequest.getRating()!= 0.0f) {
            existingReview.setRating(reviewRequest.getRating());
        }

        if (reviewRequest.getComment()!= null) {
            existingReview.setComment(reviewRequest.getComment());
        }

        repository.save(existingReview);
        updateRating(reviewRequest.getHotelId());
        return mapToDto(existingReview);
    }

    public void updateRating(String hotelId){
        float rating =  repository.findAverageRatingByHotelId(hotelId);
        hotelClient.updateRating(hotelId,rating);
    }


}
