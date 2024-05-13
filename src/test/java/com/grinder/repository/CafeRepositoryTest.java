package com.grinder.repository;

import com.grinder.domain.entity.Cafe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CafeRepositoryTest {

    @Autowired
    private CafeRepository cafeRepository;

    @DisplayName("새로운 카페의 정보를 저장")
    @Test
    void testSaveCafe() {
        //given
        Cafe cafe = Cafe.builder().name("그라인더").address("사랑시 고백구 행복동 794-2").phoneNum("01012345678").build();

        //when
        Cafe savedCafe = cafeRepository.save(cafe);

        //then
        assertThat(savedCafe.getCafeId()).isNotNull();
        assertThat(savedCafe.getCafeId()).isEqualTo(cafe.getCafeId());
        assertThat(savedCafe.getName()).isEqualTo(cafe.getName());
        assertThat(savedCafe.getAddress()).isEqualTo(cafe.getAddress());
        assertThat(savedCafe.getPhoneNum()).isEqualTo(cafe.getPhoneNum());
    }
}