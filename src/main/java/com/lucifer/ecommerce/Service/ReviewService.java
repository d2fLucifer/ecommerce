package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.Response.ReviewResponse;
import com.lucifer.ecommerce.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    ReviewDto updateReview(ReviewDto reviewDto, String id);

    void deleteReviewById(String id);

    ReviewResponse getReviewsById(String id);
    ReviewResponse getAllReviews();
}
