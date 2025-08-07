package com.promesa.promesa.domain.exhibition.dto.response;

import com.promesa.promesa.domain.exhibition.domain.Exhibition;
import com.promesa.promesa.domain.exhibition.domain.ExhibitionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ExhibitionSummary(
        Long id,
        ExhibitionStatus status,
        String title,
        String subTitle,
        String description,
        List<String> artistNames,    // 참여한 작가 목록
        LocalDate startDate,
        LocalDate endDate,
        String thumbnailImageKey,
        String thumbnailImageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionSummary of(Exhibition exhibition, List<String> artistNames, String imageUrl) {
        return new ExhibitionSummary(
                exhibition.getId(),
                exhibition.getStatus(),
                exhibition.getTitle(),
                exhibition.getSubtitle(),
                exhibition.getDescription(),
                artistNames,
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getThumbnailImageKey(),
                imageUrl,
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}