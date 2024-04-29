package com.grinder.repository;

import com.grinder.domain.enums.Role;
import com.grinder.domain.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("새로운 회원의 데이터를 저장")
    @Test
    void testSaveMember() {
        //given
        LocalDateTime beforeCreate = LocalDateTime.now();

        Member member = Member.builder().email("test@test.com").nickname("test-user-1").password("1234").build();

        //when
        Member savedUser = memberRepository.save(member);

        //then
        assertThat(savedUser.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(savedUser.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedUser.getNickname()).isEqualTo(member.getNickname());
        assertThat(savedUser.getPassword()).isEqualTo(member.getPassword());

        assertThat(savedUser.getCreatedAt()).isBefore(LocalDateTime.now());
        assertThat(savedUser.getCreatedAt()).isAfter(beforeCreate);

        assertThat(savedUser.getMemberId()).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        assertThat(member.getIsDeleted()).isEqualTo(false);

    }
}