package com.promesa.promesa.domain.item.application;

import com.promesa.promesa.common.application.S3Service;
import com.promesa.promesa.common.query.ItemQueryRepository;
import com.promesa.promesa.domain.category.dao.CategoryRepository;
import com.promesa.promesa.domain.category.domain.Category;
import com.promesa.promesa.domain.category.exception.CategoryNotFoundException;
import com.promesa.promesa.domain.home.dto.ItemPreviewResponse;
import com.promesa.promesa.domain.item.domain.Item;
import com.promesa.promesa.domain.member.dao.MemberRepository;
import com.promesa.promesa.domain.member.domain.Member;
import com.promesa.promesa.domain.member.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemQueryRepository itemQueryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final S3Service s3Service;

    @Value("${aws.s3.bucket}")  // application.yml 에 정의 필요
    private String bucketName;


    public Page<ItemPreviewResponse> findCategoryItem(Long memberId, Long categoryId, Pageable pageable) {
        // 카테고리 존재 여부 검증
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        // 멤버 존재 여부 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        Page<ItemPreviewResponse> responses = itemQueryRepository.findCategoryItem(memberId, categoryId, pageable);
        // imageKey → presigned URL 변환
        responses.forEach(response -> {
            if (response.getImageKey() != null) {
                String url = s3Service.createPresignedGetUrl(bucketName, response.getImageKey());
                response.setImageUrl(url);
            }
        });
        return responses;
    }
}
