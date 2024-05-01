package com.grinder.security.handler;

import com.google.gson.Gson;
import com.grinder.utils.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        String refreshToken = jwtUtil.generateToken(claim, 24);

        Gson gson = new Gson();

        Map<String, String> keyMap = Map.of("accessToken", accessToken, "refreshToken", refreshToken);

        String jsonStr = gson.toJson(keyMap);

        response.getWriter().println(jsonStr);
    }

}