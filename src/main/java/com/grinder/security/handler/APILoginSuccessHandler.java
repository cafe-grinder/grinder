package com.grinder.security.handler;

import com.google.gson.Gson;
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
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{
        log.info("Login Success Handler-----------------");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info(authentication.toString());
        log.info(authentication.getName());

        Map<String, Object> claim = Map.of("email", authentication.getName());
        //Access Token 유효기간 1시간
        String accessToken = jwtUtil.generateToken(claim, 1);
        //Refresh Token 유효기간 1일
        String refreshToken = jwtUtil.generateToken(claim, 24*7);

        //accessToken은 로컬 스토리지 , refreshToken은 httpOnly 쿠키에 저장
        response.addHeader("Authorization","Bearer"+accessToken);
        response.addCookie(createCookie("refresh",refreshToken));
        response.setStatus(HttpStatus.OK.value());
    }
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
