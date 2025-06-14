package com.promesa.promesa.common.dto.s3;

import com.promesa.promesa.common.consts.ImageType;

import java.util.List;
import java.util.Map;

public record PresignedUrlRequest (
        ImageType imageType,
        Long referenceId,
        List<String> fileNames,
        Map<String, String> metadata
){}