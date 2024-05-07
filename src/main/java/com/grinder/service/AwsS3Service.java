package com.grinder.service;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {
    boolean uploadImageBucket(List<MultipartFile> imgList, String contentId, ContentType contentType);

    boolean uploadFileBucket(List<MultipartFile> fileList, String contentId, ContentType contentType);

    byte[] downloadFile(String file);

    Image uploadSingleImageBucket(MultipartFile imageFile, String contentId, ContentType contentType);
}