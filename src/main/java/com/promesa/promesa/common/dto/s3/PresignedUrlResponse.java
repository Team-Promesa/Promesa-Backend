package com.promesa.promesa.common.dto.s3;

import java.util.List;

public record PresignedUrlResponse (
        String key,
        String url
){}