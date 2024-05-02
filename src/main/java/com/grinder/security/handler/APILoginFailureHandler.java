package com.grinder.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class APILoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, String> errorDetails = new HashMap<>();

        if (exception instanceof BadCredentialsException) {
            errorDetails.put("code", "로그인 실패");
            errorDetails.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorDetails.put("code", "계정 비활성화");
            errorDetails.put("message", "계정이 비활성화되었습니다. 관리자에게 문의하세요.");
        } else {
            errorDetails.put("code", "인증 실패");
            errorDetails.put("message", "인증 과정에서 오류가 발생했습니다.");
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();

        String jsonResponse = gson.toJson(errorDetails);

        response.getWriter().println(jsonResponse);
    }
}
