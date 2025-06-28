package com.promesa.promesa.domain.home.api;

import com.promesa.promesa.domain.home.application.HomeService;
import com.promesa.promesa.domain.home.dto.BrandInfoResponse;
import com.promesa.promesa.domain.home.dto.ItemPreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/brand-info")
    public ResponseEntity<BrandInfoResponse> getBrandInfo() {
        return ResponseEntity.ok(homeService.getBrandInfo());
    }

    @GetMapping("/exhibitions/{exhibitionId}/items")
    public ResponseEntity<List<ItemPreviewResponse>> getExhibitionItems(
            @PathVariable Long exhibitionId,
            @RequestParam(required = false) Long memberId // 요청 URL에 ?memberId=1 붙여야 함. 개발 테스트용
            // 로그인 기능 구현 시 @AuthenticationPrincipal
    ) {
        List<ItemPreviewResponse> response = homeService.getExhibitionItems(memberId, exhibitionId);
        return ResponseEntity.ok(response);
    }
}
