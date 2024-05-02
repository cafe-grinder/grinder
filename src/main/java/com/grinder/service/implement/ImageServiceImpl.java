package com.grinder.service.implement;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.ImageRepository;
import com.grinder.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    ImageRepository imageRepository;

    @Override
    public Image findImage(String imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new IllegalArgumentException("image id(" + imageId + ")를 찾울 수 없습니다."));
    }

    @Override
    public List<Image> findAllImage(String contentId, ContentType contentType) {
        return imageRepository.findByContentIdAndContentType(contentId, contentType);
    }

    @Override
    public void saveFeedImage(String contentId, ContentType contentType, List<String> imageUrlList) {
        for (String imageUrl : imageUrlList) {
            imageRepository.save(
                    Image.builder()
                            .imageUrl(imageUrl)
                            .contentId(contentId)
                            .contentType(contentType)
                            .build()
                    );
        }
    }

    @Override
    public void deleteFeedImage(String contentId, ContentType contentType) {
        List<Image> ImageList = findAllImage(contentId, contentType);
        imageRepository.deleteAll(ImageList);
    }
}
