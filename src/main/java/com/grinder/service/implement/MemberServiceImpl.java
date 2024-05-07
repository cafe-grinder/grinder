package com.grinder.service.implement;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Image;
import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.Role;
import com.grinder.repository.ImageRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.MemberQueryRepository;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import static com.grinder.domain.dto.MemberDTO.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberQueryRepository memberQueryRepository;

    public Member findMemberById(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("회원 아이디: " + memberId + " 인 회원이 존재하지 않습니다."));
        return member;
    }

    @Override
    public Member findMemberByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).orElseThrow(() -> new NoSuchElementException("회원 아이디" + memberEmail + "인 회원이 존재하지 않습니다."));
    }

    @Transactional
    @Override
    public MemberDTO.FindMemberAndImageDTO findMemberAndImageById(String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("회원 아이디: " + memberId + " 인 회원이 존재하지 않습니다."));
        String image = imageRepository.findByContentTypeAndContentId(ContentType.MEMBER, memberId).orElse(new Image()).getImageUrl();
        return new MemberDTO.FindMemberAndImageDTO(member, image);
    }

    @Transactional
    @Override
    public MemberDTO.FindMemberAndImageDTO findMemberAndImageByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("회원 아이디: " + email + " 인 회원이 존재하지 않습니다."));
        String image = imageRepository.findByContentTypeAndContentId(ContentType.MEMBER, member.getMemberId()).orElse(new Image()).getImageUrl();
        return new MemberDTO.FindMemberAndImageDTO(member, image);
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
