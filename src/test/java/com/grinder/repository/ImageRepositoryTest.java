package com.grinder.repository;

import com.grinder.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.UUID;
import static com.grinder.domain.ImgType.PROFILE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ImageRepositoryTest {
    @Autowired
    private ImageRepository imageRepository;

    @DisplayName("새로운 이미지를 저장")
    @Test
    void testSaveImage() {
        //given
        Image img = Image.builder().imgId(UUID.randomUUID().toString()).imgUrl("test-image-url").imgType(PROFILE).build();

        //when
        Image savedImg = imageRepository.save(img);

        //then
        assertThat(savedImg.getImgId()).isEqualTo(img.getImgId());
        assertThat(savedImg.getImgUrl()).isEqualTo(img.getImgUrl());
        assertThat(savedImg.getImgType()).isEqualTo(img.getImgType());
    }
}