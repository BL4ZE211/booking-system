package com.nitheesh.review_service.controller;

import com.nitheesh.review_service.dto.ReviewRequest;
import com.nitheesh.review_service.dto.ReviewResponse;
import com.nitheesh.review_service.service.ReviewService;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewResponse createReview(@RequestBody ReviewRequest reviewRequest){
        return reviewService.createReview(reviewRequest);
    }

    @GetMapping
    public List<ReviewResponse> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewResponse getReviewById(@PathVariable String id){
        return reviewService.getReviewById(id);
    }

    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable String id){
        reviewService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ReviewResponse updateReview(@PathVariable String id,@RequestBody ReviewRequest reviewRequest){
        return reviewService.updateReviw(id,reviewRequest);
    }
}
