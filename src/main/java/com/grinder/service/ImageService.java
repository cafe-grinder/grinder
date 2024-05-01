package com.grinder.service;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    ImageRepository imageRepository;

    public Image findImage(String imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new IllegalArgumentException("image id(" + imageId + ")를 찾울 수 없습니다."));
    }

    public List<Image> findAllImage(String contentId, ContentType contentType) {
        return imageRepository.findByContentIdAndContentType(contentId, contentType);
    }

    public void saveFeedImage(String contentId, ContentType contentType, List<String> imageUrlList) {
        for (String imageUrl : imageUrlList) {
            imageRepository.save(new Image(imageUrl, contentId, contentType));
        }
    }

    public void deleteFeedImage(String contentId, ContentType contentType) {
        List<Image> ImageList = findAllImage(contentId, contentType);
        imageRepository.deleteAll(ImageList);
    }
}
