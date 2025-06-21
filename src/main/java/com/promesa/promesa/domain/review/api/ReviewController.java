package com.promesa.promesa.domain.review.api;

import com.promesa.promesa.common.application.S3Service;
import com.promesa.promesa.common.dto.s3.PresignedUrlRequest;
import com.promesa.promesa.common.dto.s3.PresignedUrlResponse;
import com.promesa.promesa.domain.member.dao.MemberRepository;
import com.promesa.promesa.domain.member.domain.Member;
import com.promesa.promesa.domain.review.application.ReviewService;
import com.promesa.promesa.domain.review.dto.request.AddReviewRequest;
import com.promesa.promesa.domain.review.dto.response.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberRepository memberRepository;

    @PostMapping("/review-images/presigned-url")
    public ResponseEntity<List<PresignedUrlResponse>> getPresignedPutUrls(@RequestBody PresignedUrlRequest request) {
        return ResponseEntity.ok(reviewService.getPresignedPutUrls(request));
    }

    @DeleteMapping("/review-images")
    public ResponseEntity<Void> deleteReviewImage(
            @RequestParam String key
    ){
        reviewService.deleteReviewImage(key);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/items/{itemId}/reviews")
    public ResponseEntity<ReviewResponse> createReview(
            @RequestBody @Valid AddReviewRequest request,
            @PathVariable Long itemId,
            @AuthenticationPrincipal OAuth2User user
    )
    {
        // 임시 유저 객체 찾기
        String provider = (String) user.getAttribute("provider");
        String providerId = (String) user.getAttribute("providerId");
        Member member = memberRepository.findByProviderAndProviderId(provider, providerId)
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));

        ReviewResponse response = reviewService.addReview(request, itemId, member);
        return ResponseEntity.ok(response);
    }

}