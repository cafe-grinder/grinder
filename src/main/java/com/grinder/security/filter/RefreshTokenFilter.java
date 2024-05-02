package com.grinder.security.filter;

import com.google.gson.Gson;
import com.grinder.security.exception.RefreshTokenException;
import com.grinder.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final String refreshPath;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String path = request.getRequestURI();

        // 토큰 갱신 경로 아닐 경우
        if(!path.equals(refreshPath)){
            log.info("skip refresh token filter---------");
            filterChain.doFilter(request,response);
            return;
        }
        log.info("Refresh Token Filter.........run...........1");
        // 전송된 Json에서 accessToken과 refreshToken을 확인
        Map<String,String> tokens = parseRequestJSON(request);

        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        log.info("accessToken: "+ accessToken);
        log.info("refreshToken: "+refreshToken);

        try{
            checkAccessToken(accessToken);
        }catch (RefreshTokenException refreshTokenException){
            refreshTokenException.sendResponseError(response);
        }

        Map<String,Object> refreshClaims = null;

        try{
            // accessToken은 무조건 새로 발행
            // refreshToken은 만료일이 얼마 남지 않은 경우에 새로 발행
            refreshClaims = checkRefreshToken(refreshToken);
            log.info(refreshClaims.toString());

            Integer exp = (Integer) refreshClaims.get("exp");

            Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli()*1000);

            Date current = new Date(System.currentTimeMillis());

            long gapTime = (expTime.getTime()-current.getTime());

            log.info("-------------------------------------");
            log.info("current: "+current);
            log.info("expTime: "+expTime);
            log.info("gap: "+gapTime);

            String mid = (String)refreshClaims.get("mid");
            //이 상태 도달 시 무조건 accessToken은 새로 생성
            String accessTokenValue = jwtUtil.generateToken(Map.of("mid",mid),1);
            String refreshTokenValue = tokens.get("refreshToken");

            //refreshToken이 3일도 안남았을때
            if(gapTime < (1000*60*60*24*3)){
                log.info("new Refresh Token required.....");
                refreshTokenValue = jwtUtil.generateToken(Map.of("mid",mid),30);
            }
            log.info("Refresh Token result.......");
            log.info("accessToken : "+accessToken);
            log.info("refreshToken : "+refreshToken);

            sendToken(accessTokenValue,refreshTokenValue,response);

        }catch (RefreshTokenException refreshTokenException){
            refreshTokenException.sendResponseError(response);
            return;
        }
    }

    private Map<String,String> parseRequestJSON(HttpServletRequest request){
        try(Reader reader = new InputStreamReader(request.getInputStream())) {
            Gson gson = new Gson();

            return gson.fromJson(reader, Map.class);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    // accessToken 검사 -> 토큰이 없거나 잘못된 토큰인 경우 에러메세지 전송
    private void checkAccessToken(String accessToken) throws RefreshTokenException{
        try{
            jwtUtil.validateToken(accessToken);
        }catch (ExpiredJwtException expiredJwtException){
            log.info("Access Token has expired");
        }catch (Exception exception){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_ACCESS);
        }
    }

    // refreshToken 검사 -> 토큰이 없거나 잘못된 토큰인 경우 에러메세지 전송
    private Map<String,Object> checkRefreshToken(String refreshToken)throws RefreshTokenException{
        try{
            Map<String,Object> values = jwtUtil.validateToken(refreshToken);
            return values;
        }catch (ExpiredJwtException expiredJwtException){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
        }catch (MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException------------------------");
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        }catch (Exception exception){
            new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        }
        return null;
    }

    //만들어진 토큰들 전송
    private void sendToken(String accessTokenValue, String refreshTokenValue,HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(Map.of("accessToken",accessTokenValue,
                "refreshToken",refreshTokenValue));

        try{
            response.getWriter().println(jsonStr);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
