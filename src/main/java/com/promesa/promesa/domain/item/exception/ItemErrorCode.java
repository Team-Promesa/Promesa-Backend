package com.promesa.promesa.domain.item.exception;

import com.promesa.promesa.common.dto.ErrorReason;
import com.promesa.promesa.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.promesa.promesa.common.consts.PromesaStatic.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ItemErrorCode implements BaseErrorCode {
    ITEM_NOT_FOUND(NOT_FOUND, "Item_404", "존재하지 않는 상품입니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder()
                .reason(reason)
                .code(code)
                .status(status)
                .build();
    }
}
