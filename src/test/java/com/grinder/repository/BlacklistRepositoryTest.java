package com.grinder.repository;

import com.grinder.domain.entity.Blacklist;
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
class BlacklistRepositoryTest {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("블랙리스트 추가")
    @Test
    void testSaveBlacklist() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Blacklist saved = blacklistRepository.save(Blacklist.builder().member(member).blockedMember(member).build());

        Blacklist found = blacklistRepository.findById(1L).orElseThrow();

        Assertions.assertEquals(saved.getMember().getMemberId(), found.getMember().getMemberId());
    }
}