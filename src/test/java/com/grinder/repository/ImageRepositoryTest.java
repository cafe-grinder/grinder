package com.grinder.repository;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;

    @DisplayName("새로운 이미지를 저장")
    @Test
    void testSaveImage() {
        //given
        Image image = Image.builder().imageUrl("test-image-url").contentId(UUID.randomUUID().toString()).contentType(ContentType.MEMBER).build();

        //when
        Image savedImage = imageRepository.save(image);

        //then
        assertThat(savedImage.getImageId()).isNotNull();
        assertThat(savedImage.getImageId()).isEqualTo(image.getImageId());
        assertThat(savedImage.getImageUrl()).isEqualTo(image.getImageUrl());
        assertThat(savedImage.getContentId()).isEqualTo(image.getContentId());
        assertThat(savedImage.getContentType()).isEqualTo(image.getContentType());
    }
}