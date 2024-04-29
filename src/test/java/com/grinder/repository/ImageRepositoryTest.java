package com.grinder.repository;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;

    @DisplayName("새로운 이미지를 저장")
    @Test
    void testSaveImage() {
        //given
        Image img = Image.builder().imageUrl("test-image-url").contentType(ContentType.MEMBER).build();

        //when
        Image savedImg = imageRepository.save(img);

        //then
        assertThat(savedImg.getImageId()).isNotNull();
        assertThat(savedImg.getImageId()).isEqualTo(img.getImageId());
        assertThat(savedImg.getImageUrl()).isEqualTo(img.getImageUrl());
        assertThat(savedImg.getContentType()).isEqualTo(img.getContentType());
    }
}