package com.promesa.promesa.domain.item.exception;

import com.promesa.promesa.common.exception.PromesaCodeException;

public class ItemNotFoundException extends PromesaCodeException {
    public static final PromesaCodeException EXCEPTION = new ItemNotFoundException();
    private ItemNotFoundException() {
        super(ItemErrorCode.ITEM_NOT_FOUND);
    }
}
