package com.promesa.promesa.common.application;

import com.promesa.promesa.common.consts.ImageType;
import com.promesa.promesa.common.dto.s3.PresignedUrlResponse;
import com.promesa.promesa.common.exception.InternalServerError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Presigner s3Presigner;
    private final S3Client s3Client;

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

    public List<PresignedUrlResponse> createPresignedPutUrl(
            String bucketName,
            ImageType imageType,
            Long referenceId,
            List<String> fileNames,
            Map<String, String> metadata
    ) {
        try {
            return fileNames.stream()
                    .map(originalFileName -> {
                        try {
                            String key = generateKey(imageType,  referenceId, originalFileName);

                            PutObjectRequest objectRequest = PutObjectRequest.builder()
                                    .bucket(bucketName)
                                    .key(key)
                                    .metadata(metadata)
                                    .build();

                            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                    .signatureDuration(Duration.ofMinutes(expireMinutes))
                                    .putObjectRequest(objectRequest)
                                    .build();

                            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
                            log.info("Presigned URL 생성: {}", presignedRequest.url());

                            return new PresignedUrlResponse(key, presignedRequest.url().toExternalForm());
                        } catch (Exception e) {
                            log.error("Presigned URL 생성 실패", e);
                            throw InternalServerError.EXCEPTION;
                        }
                    })
                    .toList();
        } catch (Exception e) {
            log.error("Presigned URL 생성 실패", e);
            throw InternalServerError.EXCEPTION;
        }
    }

    private String generateKey(ImageType imageType, Long referenceId, String originalFileName) {
        String uuid = UUID.randomUUID().toString();

        return switch (imageType) {
            case REVIEW -> "reviews/" + referenceId + "/" + uuid + "-" + originalFileName;
            case PROFILE -> "profiles/" + referenceId + "/" + uuid + "-" + originalFileName;
            case ITEM -> "items/" + referenceId + "/" + uuid + "-" + originalFileName;
        };
    }

    public void deleteObject(String bucketName, String key) {
        try {
            s3Client.deleteObject(builder -> builder
                    .bucket(bucketName)
                    .key(key)
                    .build()
            );
            log.info("S3 객체 삭제 완료: {}/{}", bucketName, key);
        } catch (Exception e) {
            log.error("S3 객체 삭제 실패: {}/{}", bucketName, key, e);
            throw InternalServerError.EXCEPTION;
        }
    }
}