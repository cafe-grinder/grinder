package com.grinder.service.implement;

import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.Role;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.MemberQueryRepository;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import static com.grinder.domain.dto.MemberDTO.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public Member findMemberById(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("회원 아이디: " + memberId + " 인 회원이 존재하지 않습니다."));
        return member;
    }

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

    @Override
    @Transactional
    public void updateMemberIsDeleted(String memberId) {
        Member member = findMemberById(memberId);

        member.switchIsDeleted();
    }

    @Override
    public List<FindMemberDTO> searchMemberSlice(String role, String nickname, Pageable pageable) {
        return memberQueryRepository.searchMemberByRoleAndNicknameSlice(role, nickname, pageable).getContent();
    }
}
