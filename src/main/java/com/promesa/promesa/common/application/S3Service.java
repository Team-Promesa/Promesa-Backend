package com.promesa.promesa.common.application;

import com.promesa.promesa.common.exception.InternalServerError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Presigner s3Presigner;

    @Value("${aws.s3.presigned.expire-minutes}")
    private long expireMinutes;

    /**
     * PresignedUrl 생성
     * @param bucketName    S3 버킷 이름
     * @param keyName   객체 키
     * @return  유효한 presigned URL
     */
    public String createPresignedGetUrl(String bucketName, String keyName) {
        try {

            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(expireMinutes))  // 만료 시간 설정
                    .getObjectRequest(objectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);

            log.info("Presigned URL: [{}]", presignedRequest.url().toString());
            log.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

            return presignedRequest.url().toExternalForm();

        }catch (Exception e){
            log.error("Presigned URL 생성 실패: {}/{}", bucketName, keyName, e);
            throw InternalServerError.EXCEPTION;
        }
    }

}
