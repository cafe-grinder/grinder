package com.grinder.service;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.Role;
import com.grinder.repository.MemberRepository;
import com.grinder.service.implement.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    MemberRepository memberRepository;

    @DisplayName("전체 회원 조회 테스트")
    @Test
    void testFindAllMembers() {
        //given
        Member member1 = Member.builder().email("test1@test.com").nickname("member1").password("qwer1234").phoneNum("01012345678").role(Role.MEMBER).build();
        Member member2 = Member.builder().email("test2@test.com").nickname("member2").password("qwer1234").phoneNum("01043218765").role(Role.MEMBER).build();

        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);

        doReturn(memberList).when(memberRepository).findAll();

        //when
        List<MemberDTO.FindMemberDTO> memberDTOList = memberService.findAllMembers();

        //then
        assertThat(memberDTOList.size()).isEqualTo(2);
        assertThat(memberDTOList.get(0).getMemberId()).isNull();
    }

    @DisplayName("회원 권한 변경 테스트")
    @Test
    void testUpdateMemberRole() {
        //given
        Member member = Member.builder().role(Role.MEMBER).build();
        Member verifiedMember = Member.builder().role(Role.VERIFIED_MEMBER).build();
        Member sellerMember = Member.builder().role(Role.SELLER).build();
        Member adminMember = Member.builder().role(Role.ADMIN).build();

        doReturn(Optional.of(member)).when(memberRepository).findById("1");
        doReturn(Optional.of(verifiedMember)).when(memberRepository).findById("2");
        doReturn(Optional.of(sellerMember)).when(memberRepository).findById("3");
        doReturn(Optional.of(adminMember)).when(memberRepository).findById("4");

        //when
        memberService.updateMemberRole("1");
        memberService.updateMemberRole("2");
        memberService.updateMemberRole("3");
        memberService.updateMemberRole("4");

        //then
        assertThat(member.getRole()).isEqualTo(Role.VERIFIED_MEMBER);
        assertThat(verifiedMember.getRole()).isEqualTo(Role.MEMBER);
        assertThat(sellerMember.getRole()).isEqualTo(Role.SELLER);
        assertThat(adminMember.getRole()).isEqualTo(Role.ADMIN);
    }

    @DisplayName("회원 삭제/취소 변경 테스트")
    @Test
    void testUpdateMemberIsDeleted() {
        //given
        Member aliveMember = Member.builder().isDeleted(false).build();
        Member deletedMember = Member.builder().isDeleted(true).build();

        doReturn(Optional.of(aliveMember)).when(memberRepository).findById("1");
        doReturn(Optional.of(deletedMember)).when(memberRepository).findById("2");

        //when
        memberService.updateMemberIsDeleted("1");
        memberService.updateMemberIsDeleted("2");

        //then
        assertThat(aliveMember.getIsDeleted()).isTrue();
        assertThat(deletedMember.getIsDeleted()).isFalse();
    }
}