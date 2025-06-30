package com.nitheesh.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String userId;
    private String hotelId;
    private float rating;
    private String  comment;
}
