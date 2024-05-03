package com.grinder.service.implement;

import com.grinder.domain.dto.CafeSummaryDTO;
import com.grinder.domain.entity.CafeSummary;
import com.grinder.repository.CafeSummaryRepository;
import com.grinder.utils.AlanAPI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({CafeSummaryServiceImpl.class, AlanAPI.class})
@Slf4j
@ActiveProfiles("test")
class CafeSummaryServiceImplTest {
    @Autowired
    private CafeSummaryServiceImpl cafeSummaryService;
    @Autowired
    private CafeSummaryRepository cafeSummaryRepository;
    private CafeSummary cafeSummary;

    @BeforeEach
    void setUp() {
        cafeSummary = cafeSummaryRepository.save(CafeSummary.builder().cafeId("1234").summary("독특한 콘셉트의 케이크를 파는 카페로, 강남역에서 수다 떨 수 있는 장소로 추천됩니다").build());
    }

    @Test
    void deleteCafeSummary() {
        boolean result = cafeSummaryService.deleteCafeSummary("1234");

        assertTrue(result);
    }
}