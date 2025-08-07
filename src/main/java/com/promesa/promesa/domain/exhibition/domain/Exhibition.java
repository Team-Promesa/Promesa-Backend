package com.promesa.promesa.domain.exhibition.domain;

import com.promesa.promesa.common.domain.BaseTimeEntity;
import com.promesa.promesa.common.exception.ValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="exhibition")
public class Exhibition extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String subtitle;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "exhibition_status", nullable = false)
    private ExhibitionStatus status;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<ExhibitionImage> exhibitionImages = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExhibitionItem> exhibitionItems = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExhibitionArtist> exhibitionArtists = new ArrayList<>();

    public void setStatus(ExhibitionStatus status) {
        this.status = status;
    }

    public String getThumbnailImageKey() {
        return exhibitionImages.stream()
                .filter(ExhibitionImage::isThumbnail)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("썸네일 이미지가 존재하지 않습니다"))
                .getImageKey();
    }

    public List<ExhibitionImage> getDetailImages() {
        return exhibitionImages.stream()
                .filter(img -> !img.isThumbnail())
                .sorted(Comparator.comparingInt(ExhibitionImage::getSortOrder))
                .toList();
    }

    @Builder
    public Exhibition(String title, String subtitle, String description, LocalDate startDate, LocalDate endDate, ExhibitionStatus status) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void addExhibitionImage(ExhibitionImage exhibitionImage) {
        exhibitionImage.setExhibition(this);
        this.exhibitionImages.add(exhibitionImage);
    }

    public void addExhibitionItem(ExhibitionItem exhibitionItem) {
        exhibitionItem.setExhibition(this);
        this.exhibitionItems.add(exhibitionItem);
    }

    public void addExhibitionArtist(ExhibitionArtist exhibitionArtist) {
        exhibitionArtist.setExhibition(this);
        this.exhibitionArtists.add(exhibitionArtist);
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw ValidationException.EXCEPTION;
        }
        this.title = title;
    }

    public void setSubtitle(String subTitle) {
        if (subTitle == null || subTitle.isBlank()) {
            throw ValidationException.EXCEPTION;
        }
        this.subtitle = subTitle;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw ValidationException.EXCEPTION;
        }
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw ValidationException.EXCEPTION;
        }
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
