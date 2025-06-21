package com.promesa.promesa.domain.item.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.domain.artist.domain.Artist;
import com.promesa.promesa.domain.image.domain.ItemImage;
import com.promesa.promesa.domain.itemCategory.domain.ItemCategory;
import com.promesa.promesa.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private int price;
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "sale_status")
    private SaleStatus saleStatus;

    @NotNull
    @Column(name = "wish_count")
    private int wishCount = 0;

    @Column(name = "average_rating")
    private Double averageRating;   // 리뷰가 없을 경우 null 가능

    @Column(name = "review_count")
    private int reviewCount = 0;

    @Column(name = "total_rating")
    private double totalRating = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ExhibitionItem> exhibitionItems = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemImage> itemImages = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public void increaseReviewCount() {
        this.reviewCount++;
    }

    public void decreaseReviewCount() {
        this.reviewCount--;
    }

    public void increaseTotalRating(int newRating) {
        this.totalRating += newRating;
    }

    public void decreaseTotalRating(int newRating) {
        this.totalRating -= newRating;
    }

    public void addReview(int newRating) {
        increaseReviewCount();
        increaseTotalRating(newRating);
        updateAverageRating();
    }

    public void removeReview(int newRating) {
        decreaseReviewCount();
        decreaseTotalRating(newRating);
        updateAverageRating();
    }

    public void updateAverageRating() {
        if (this.reviewCount == 0) {
            this.averageRating = 0.0;
        }
        else {
            this.averageRating = this.totalRating / this.reviewCount;
        }
    }
}