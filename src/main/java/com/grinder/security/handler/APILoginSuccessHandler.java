package com.grinder.security.handler;

import com.google.gson.Gson;
import com.grinder.domain.entity.RefreshEntity;
import com.grinder.repository.RefreshRepository;
import com.grinder.security.dto.CustomOauth2Member;
import com.grinder.security.dto.CustomUserDetails;
import com.grinder.utils.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.awt.*;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{
        log.info("Login Success Handler-----------------");


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info(String.valueOf(authentication.getPrincipal().getClass()));
        String email = null;
        if(authentication.getPrincipal() instanceof CustomOauth2Member){
            CustomOauth2Member customOauth2Member = (CustomOauth2Member) authentication.getPrincipal();
            email = customOauth2Member.getEmail();
        } else{
            CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
            email = customUserDetails.getUsername();
        }

        Map<String, Object> claim = Map.of("email", email);
        //Access Token 유효기간 1시간
        String accessToken = jwtUtil.generateToken(claim, 1);
        //Refresh Token 유효기간 1일
        String refreshToken = jwtUtil.generateToken(claim, 24*7);

        addRefreshEntity(authentication.getName(),refreshToken,24&7);


        //accessToken은 로컬 스토리지 , refreshToken은 httpOnly 쿠키에 저장
        response.addCookie(createCookie("access",accessToken));
        response.addCookie(createCookie("refresh",refreshToken));
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect("/");
    }
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(false);

        return cookie;
    }

    private void addRefreshEntity(String email, String refresh,int time) {

        RefreshEntity refreshEntity = RefreshEntity.builder()
                .refresh(refresh)
                .email(email)
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()).toString())
                .build();

        refreshRepository.save(refreshEntity);
    }

}