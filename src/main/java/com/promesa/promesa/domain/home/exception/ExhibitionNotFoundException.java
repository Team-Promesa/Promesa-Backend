package com.promesa.promesa.domain.home.exception;

import com.promesa.promesa.common.exception.PromesaCodeException;

public class ExhibitionNotFoundException extends PromesaCodeException {
    public static final PromesaCodeException EXCEPTION = new ExhibitionNotFoundException();
    private ExhibitionNotFoundException() {
        super(HomeErrorCode.EXHIBITION_NOT_FOUND);
    }
}