package com.grinder.service.implement;

import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.Role;
import com.grinder.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MemberServiceImplTest {
    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    MemberRepository memberRepository;

    @DisplayName("회원 아이디로 조회")
    @Test
    void testFindMemberById() {
        Member member = Member.builder().memberId(UUID.randomUUID().toString()).build();

        doReturn(Optional.of(member)).when(memberRepository).findById(member.getMemberId());

        Member memberFound =  memberService.findMemberById(member.getMemberId());

        assertThat(memberFound).isEqualTo(member);
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