package com.grinder.service.implement;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.ImageRepository;
import com.grinder.repository.queries.ImageQueryRepository;
import com.grinder.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageQueryRepository imageQueryRepository;

    @Override
    public Image findImage(String imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new IllegalArgumentException("image id(" + imageId + ")를 찾울 수 없습니다."));
    }

    @Override
    public String findImageUrlByContentId(String id) {
        return imageQueryRepository.findImageUrlByContentId(id);
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

    @Override
    public Image findImageByImageUrl(String imageUrl) {
        return imageRepository.findByImageUrl(imageUrl).orElseThrow(() -> new NoSuchElementException("Url: " + imageUrl + "에 해당하는 이미지가 존재하지 않습니다."));
    }
}
