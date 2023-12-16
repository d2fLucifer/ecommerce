package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto, Long productId,Long userID);

    ReviewDto updateReview(ReviewDto reviewDto, Long reviewId, Long productId,Long userId );

    void deleteReviewById(Long reviewsId, Long productId,Long userId);

    ReviewDto getReviewsById(Long id, Long productId);

    List<ReviewDto> getAllReviewsByProductId(Long productId);
}
