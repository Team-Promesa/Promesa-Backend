package com.promesa.promesa.domain.item.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.domain.artist.domain.Artist;
import com.promesa.promesa.domain.itemCategory.domain.ItemCategory;
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
    @Id @GeneratedValue
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
    private int wishCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ExhibitionItem> exhibitionItems = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemImage> itemImages = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategories = new ArrayList<>();
}
