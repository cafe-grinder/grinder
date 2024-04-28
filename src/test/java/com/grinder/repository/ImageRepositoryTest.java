package com.grinder.repository;

import com.grinder.domain.Image;
import com.grinder.domain.ImgType;
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
        Image img = Image.builder().imgUrl("test-image-url").imgType(ImgType.PROFILE).build();

        //when
        Image savedImg = imageRepository.save(img);

        //then
        assertThat(savedImg.getImgId()).isNotNull();
        assertThat(savedImg.getImgId()).isEqualTo(img.getImgId());
        assertThat(savedImg.getImgUrl()).isEqualTo(img.getImgUrl());
        assertThat(savedImg.getImgType()).isEqualTo(img.getImgType());
    }
}