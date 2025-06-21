package com.promesa.promesa.domain.review.dao;

import com.promesa.promesa.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByItemIdAndMemberId(Long itemId, Long memberId);
}
