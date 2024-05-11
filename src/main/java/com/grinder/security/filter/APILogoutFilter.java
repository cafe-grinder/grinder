package com.grinder.security.filter;

import com.grinder.repository.RefreshRepository;
import com.grinder.security.exception.AccessTokenException;
import com.grinder.security.exception.RefreshTokenException;
import com.grinder.utils.JWTUtil;
import com.grinder.utils.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static java.awt.SystemColor.window;

@Slf4j
@RequiredArgsConstructor
public class APILogoutFilter extends GenericFilterBean {
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final RedisUtil redisUtil;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //path and method verify
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("/api/logout")) {

            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {

            filterChain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        //refresh null check
        if (refresh == null) {

            throw new RefreshTokenException(RefreshTokenException.ErrorCase.BAD_ACCESS);
        }
        log.info("refreshToken: "+refresh);

        //expired check
//        try {
//            jwtUtil.validateToken(refresh);
//        } catch (ExpiredJwtException e) {
//
//            //response status code
//            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
//        }


        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {
            Cookie reCookie = new Cookie("refresh", null);
            reCookie.setMaxAge(0);
            reCookie.setPath("/");

            Cookie acCookie = new Cookie("access", null);
            acCookie.setMaxAge(0);
            acCookie.setPath("/");

            response.addCookie(reCookie);
            response.addCookie(acCookie);
            //response status code
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
        }

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshRepository.deleteByRefresh(refresh);

        // accessToken blacklist에 추가
        String accessToken = cutOffBearer(getAccess(request, response));
        redisUtil.setBlackList(accessToken,"accessToken",60);

        //Refresh 토큰 Cookie 값 0
        Cookie reCookie = new Cookie("refresh", null);
        reCookie.setMaxAge(0);
        reCookie.setPath("/");

        Cookie acCookie = new Cookie("access", null);
        acCookie.setMaxAge(0);
        acCookie.setPath("/");

        response.addCookie(reCookie);
        response.addCookie(acCookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String getAccess(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("access")) {
                accessToken = "Bearer " + cookie.getValue();
                return accessToken;
            }
        }
        return null;
    }

    private String cutOffBearer(String accessToken) {
        if(accessToken == null||accessToken.length() < 8){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }
        String tokenStr = accessToken.substring(7);

        return tokenStr;
    }
}
