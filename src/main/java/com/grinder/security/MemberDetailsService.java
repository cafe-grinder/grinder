package com.grinder.security;

import com.grinder.domain.entity.Member;
import com.grinder.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new NullPointerException("회원 정보가 존재하지 않습니다. : "+email));
        // TODO : 탈퇴한 회원 예외처리하기
        return new CustomUserDetails(member);
    }
}
