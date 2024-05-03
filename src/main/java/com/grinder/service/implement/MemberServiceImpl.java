package com.grinder.service.implement;

import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.Role;
import com.grinder.repository.MemberRepository;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import static com.grinder.domain.dto.MemberDTO.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public List<FindMemberDTO> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        List<FindMemberDTO> memberDTOList = memberList.stream().map(member -> new FindMemberDTO(member)).toList();

        return memberDTOList;
    }

    @Override
    public Member findMemberById(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("회원 아이디" + memberId + "인 회원이 존재하지 않습니다."));
        return member;
    }

    @Override
    public Member findMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).orElseThrow(() -> new NoSuchElementException("회원 아이디" + memberEmail + "인 회원이 존재하지 않습니다."));
    }

    // Todo. 선택한 회원이 관리자거나 판매자일 경우 - 버튼을 disable 처리
    @Override
    @Transactional
    public void updateMemberRole(String memberId) {
        Member member = findMemberById(memberId);

        if (member.getRole() == Role.MEMBER) {
            member.verify();
        } else if (member.getRole() == Role.VERIFIED_MEMBER) {
            member.cancelVerify();
        }
    }

    // Todo. isDeleted 값에 따라 버튼 색 바꾸기
    @Override
    @Transactional
    public void updateMemberIsDeleted(String memberId) {
        Member member = findMemberById(memberId);

        member.switchIsDeleted();
    }

    @Override
    public List<FindMemberDTO> searchMemberByNickname(String nickname) {
        List<Member> memberList = memberRepository.searchMemberByNickname(nickname);
        List<FindMemberDTO> memberDTOList = memberList.stream().map(member -> new FindMemberDTO(member)).toList();
        return memberDTOList;
    }

    @Override
    public boolean addMember(MemberRequestDto request){
        Member member = Member.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNum(request.getPhoneNum())
                .build();
        memberRepository.save(member);
        return true;
    }
}
