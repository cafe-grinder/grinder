package com.grinder.security.service;

import com.grinder.domain.entity.Member;
import com.grinder.exception.UserRegistrationException;
import com.grinder.repository.MemberRepository;
import com.grinder.security.dto.CustomOauth2Member;
import com.grinder.security.dto.GoogleResponse;
import com.grinder.security.dto.NaverResponse;
import com.grinder.security.dto.OAuth2Response;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User =  super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if(registrationId.equals("google")){
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else{
            return null;
        }
        String email = oAuth2Response.getEmail();
        if(memberRepository.existsByEmail(email)){
            // DB에 유저 정보 있을 시
            Member member = memberRepository.findByEmail(email).orElse(null);
            return new CustomOauth2Member(member);
        } else{
            //DB에 유저 정보 없을 시 -> 빠져나와서 회원가입 유도 -> 이메일만 넣어주기 -> 보안상의 이유로 권장은 안함..
            throw new UserRegistrationException("User not registered", email);

        }
    }

}
