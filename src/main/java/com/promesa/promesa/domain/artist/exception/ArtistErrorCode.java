package com.promesa.promesa.domain.artist.exception;

import com.promesa.promesa.common.dto.ErrorReason;
import com.promesa.promesa.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.promesa.promesa.common.consts.PromesaStatic.*;

@Getter
@AllArgsConstructor
public enum ArtistErrorCode implements BaseErrorCode {
    ARTIST_NOT_FOUND(NOT_FOUND, "Artist_404", "해당 작가를 찾을 수 없습니다.");
    /*
    이 중에 필요한 거만 사용 예정
    ARTIST_ALREADY_EXISTS(CONFLICT, "Artist_409", "이미 등록된 작가입니다."),
    UNAUTHORIZED_ARTIST_ACCESS(FORBIDDEN, "Artist_403", "작가 정보에 접근할 권한이 없습니다."),
    INVALID_INSTAGRAM_URL(BAD_REQUEST, "Artist_400", "유효하지 않은 인스타그램 주소입니다."),
    ARTIST_IMAGE_NOT_FOUND(NOT_FOUND, "Artist_404_IMG", "해당 이미지를 찾을 수 없습니다."),
    ARTIST_IMAGE_LIMIT_EXCEEDED(BAD_REQUEST, "Artist_400_IMG", "이미지 업로드 가능 수를 초과했습니다."),
    INVALID_ARTIST_STATUS(BAD_REQUEST, "Artist_400_STATUS", "유효하지 않은 작가 상태입니다."),
    ARTIST_DELETION_FAILED(INTERNAL_SERVER, "Artist_500_DELETE", "작가 삭제에 실패했습니다.");
     */

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder()
                .reason(reason)
                .code(code)
                .status(status)
                .build();
    }
}
