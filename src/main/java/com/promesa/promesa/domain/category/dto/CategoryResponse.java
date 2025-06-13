package com.promesa.promesa.domain.category.dto;

import com.promesa.promesa.domain.category.domain.Category;

public record CategoryResponse(Long id, String name) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
