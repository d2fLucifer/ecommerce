package com.lucifer.ecommerce.controller;

import com.lucifer.ecommerce.Service.ReviewService;
import com.lucifer.ecommerce.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/products/{productId}/users/{userId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable(name = "productId") Long productId,
                                                  @PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(reviewService.createReview(reviewDto, productId, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}/products/{productId}/users/{userId}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable(name = "reviewId") Long reviewId,
                                                  @PathVariable(name = "productId") Long productId,
                                                  @PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(reviewService.updateReview(reviewDto, reviewId, productId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}/products/{productId}/users/{userId}")
    public ResponseEntity<String> deleteReview(@PathVariable(name = "reviewId") Long reviewId,
                                               @PathVariable(name = "productId") Long productId,
                                               @PathVariable(name = "userId") Long userId) {
        reviewService.deleteReviewById(reviewId, productId, userId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{reviewId}/products/{productId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(name = "reviewId") Long reviewId,
                                                   @PathVariable(name = "productId") Long productId) {
        return new ResponseEntity<>(reviewService.getReviewsById(reviewId, productId), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByProductId(@PathVariable(name = "productId") Long productId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByProductId(productId), HttpStatus.OK);
    }
}
