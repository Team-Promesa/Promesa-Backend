package com.promesa.promesa.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddReviewRequest {

        @NotBlank(message = "내용은 비어 있을 수 없습니다.")
        @Size(min = 10, max = 1000, message = "내용은 최소 10자, 최대 1000자입니다.")
        private String content;

        @Min(value = 1, message = "별점은 최소 1점입니다.")
        @Max(value = 5, message = "별점은 최대 5점입니다.")
        private int rating;

        @Size(max = 3, message = "이미지는 최대 3장입니다.")
        private List<String> imageKeys;
}
