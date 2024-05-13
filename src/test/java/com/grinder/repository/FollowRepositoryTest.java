package com.grinder.repository;

import com.grinder.domain.entity.CafeRegister;
import com.grinder.domain.entity.Follow;
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
class FollowRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FollowRepository followRepository;
    @DisplayName("팔로우 추가")
    @Test
    void testSaveFollow() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Follow saved = followRepository.save(Follow.builder().member(member).following(member).build());

        Follow found = followRepository.findById(saved.getFollowId()).orElseThrow();

        Assertions.assertEquals(saved.getMember().getMemberId(), found.getMember().getMemberId());
    }
}