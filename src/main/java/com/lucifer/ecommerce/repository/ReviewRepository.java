package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
