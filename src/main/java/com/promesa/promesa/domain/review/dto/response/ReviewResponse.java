package com.promesa.promesa.domain.review.dto.response;

import com.promesa.promesa.common.application.S3Service;
import com.promesa.promesa.domain.review.domain.Review;

import java.util.List;

public record ReviewResponse(
        Long reviewId,
        String content,
        Long itemId,
        Long reviewerId,
        int rating,
        List<String> reviewImages
){
    public static ReviewResponse from(Review review, S3Service s3Service, String bucket) {
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getItem().getId(),
                review.getMember().getId(),
                review.getRating(),
                review.getReviewImages().stream()
                        .map(image -> s3Service.createPresignedGetUrl(bucket, image.getKey()))
                        .toList()
        );
    }
}