package com.promesa.promesa.domain.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage {
    @Id @GeneratedValue
    @Column(name = "review_image_id")
    private Long id;

    @Column(name = "image_key")
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public void setReview(Review review) {
        this.review = review;
    };

    @Builder
    public ReviewImage(String key) {
        this.key = key;
    }
}