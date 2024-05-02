package com.grinder.repository;

import com.grinder.domain.enums.Role;
import com.grinder.domain.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;

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

        Member member = Member.builder().email("test@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build();

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
        assertThat(savedUser.getRole()).isEqualTo(Role.MEMBER);
        assertThat(member.getIsDeleted()).isEqualTo(false);
    }

    @DisplayName("닉네임으로 회원 검색 테스트")
    @Test
    void testSearchMemberByNickname() {
        //given
        Member member1 = Member.builder().email("test1@test.com").nickname("member123").password("1234").phoneNum("01012345678").build();
        Member member2 = Member.builder().email("test2@test.com").nickname("member234").password("1234").phoneNum("01023456789").build();
        Member member3 = Member.builder().email("test3@test.com").nickname("member456").password("1234").phoneNum("01012345678").build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> memberList = memberRepository.searchMemberByNickname("3");

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }
}