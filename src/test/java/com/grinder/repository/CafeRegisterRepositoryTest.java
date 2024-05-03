package com.grinder.repository;

import com.grinder.domain.entity.Bookmark;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.CafeRegister;
import com.grinder.domain.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CafeRegisterRepositoryTest {

    @Autowired
    private CafeRegisterRepository cafeRegisterRepository;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("카페 등록 신청")
    @Test
    void testSaveCafeRegister() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        CafeRegister saved = cafeRegisterRepository.save(CafeRegister.builder().member(member).name("스타벅스").address("서울 서초구").build());

        CafeRegister found = cafeRegisterRepository.findById(saved.getRegisterId()).orElseThrow();

        Assertions.assertEquals(saved.getMember().getMemberId(), found.getMember().getMemberId());
    }
}