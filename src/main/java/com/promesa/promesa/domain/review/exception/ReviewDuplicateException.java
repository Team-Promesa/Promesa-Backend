package com.promesa.promesa.domain.review.exception;

import com.promesa.promesa.common.exception.PromesaCodeException;

public class ReviewDuplicateException extends PromesaCodeException {
    public static PromesaCodeException EXCEPTION = new ReviewDuplicateException();
    private ReviewDuplicateException() {
        super(ReviewErrorCode.REVIEW_DUPLICATE);
    }
}