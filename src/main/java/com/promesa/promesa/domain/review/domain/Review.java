package com.promesa.promesa.domain.review.domain;

import com.promesa.promesa.domain.item.domain.Item;
import com.promesa.promesa.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String content;

    private int rating = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    public void addReviewImage(ReviewImage image) {
        reviewImages.add(image);
        image.setReview(this);
    }

    @Builder
    private Review(String content, int rating, Item item, Member member) {
        this.content = content;
        this.rating = rating;
        this.item = item;
        this.member = member;
    }
}
