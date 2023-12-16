package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.ReviewService;
import com.lucifer.ecommerce.dto.ReviewDto;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.Review;
import com.lucifer.ecommerce.model.User;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.repository.ReviewRepository;
import com.lucifer.ecommerce.repository.UserRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final GenericMapper genericMapper;
    private final EntityManager entityManager;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Review review = genericMapper.map(reviewDto, Review.class);


        review.setUser(user);
        review.setDate(new Date());
        if (review.getProducts() == null) {
            review.setProducts(new ArrayList<>());
        }
        review.getProducts().add(product);
        Review save = reviewRepository.save(review);
        user.getReviews().add(save);
        product.getReviews().add(save);


        userRepository.save(user);
        productRepository.save(product);


        return genericMapper.map(save, ReviewDto.class);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId, Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Review review = genericMapper.map(reviewDto, Review.class);
        review.setUpdatedDate(new Date());


        ReviewDto map = genericMapper.map(reviewRepository.save(review), ReviewDto.class);
        map.setUserId(user.getId());
        return map;
    }

    @Override
    public void deleteReviewById(Long reviewsId, Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Review review = reviewRepository.findById(reviewsId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewsId));


        user.getReviews().removeIf(review1 -> review1.getId().equals(reviewsId));
        product.getReviews().removeIf(review1 -> review1.getId().equals(reviewsId));
        userRepository.save(user);
        productRepository.save(product);
        reviewRepository.deleteById(reviewsId);


    }

    @Override
    public ReviewDto getReviewsById(Long id, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));

        return genericMapper.map(review, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getAllReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return genericMapper.mapList(product.getReviews(), ReviewDto.class);
    }
}
