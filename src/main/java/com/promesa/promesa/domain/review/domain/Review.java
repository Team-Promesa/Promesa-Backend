package com.promesa.promesa.domain.review.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.domain.item.domain.Item;
import com.promesa.promesa.domain.member.domain.Member;
import com.promesa.promesa.domain.order.domain.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="review")
public class Review extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Lob
    private String content;

    private int rating = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "order_item_id", unique = true, nullable = false)
    private OrderItem orderItem;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    public void addReviewImage(ReviewImage image) {
        this.reviewImages.add(image);
        image.setReview(this);
    }

    public void setReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages.clear();  // 기존 리뷰 이미지 초기화
        for (ReviewImage reviewImage : reviewImages) {
            addReviewImage(reviewImage);
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Builder
    private Review(String content, int rating, Member member, OrderItem orderItem, Item item) {
        this.content = content;
        this.rating = rating;
        this.member = member;
        this.item = item;
        this.orderItem = orderItem;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
