package com.grinder.security.filter;

import com.grinder.security.CustomUserDetails;
import com.grinder.security.MemberDetailsService;
import com.grinder.security.exception.AccessTokenException;
import com.grinder.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Currency;
import java.util.Map;

// 현재 사용자가 로그인한 사용자인지 체크 -> JWT 토큰을 검사
@Slf4j
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

    //JWTUtil의 validateToken() 활용
    private final JWTUtil jwtUtil;
    private final MemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{

        String header = request.getHeader("access");
        if (header != null) {
            String path = request.getRequestURI();
            if (path.startsWith("/")) {
                filterChain.doFilter(request, response);
                return;
            }

            log.info("Token Check Filter...................");
            log.info("JWTUtil: " + jwtUtil);

            try {
                validateAccessToken(header);
                String email = jwtUtil.getEmail(header);
                UserDetails userDetails = memberDetailsService.loadUserByUsername(email);

                if(userDetails != null){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                filterChain.doFilter(request, response);
            } catch (AccessTokenException accessTokenException) {
                accessTokenException.sendResponseError(response);
            }
        }
        filterChain.doFilter(request, response);
    }
    // AccessToken 검증

        private Map<String,Object> validateAccessToken(String accessToken) throws AccessTokenException{

        if(accessToken == null||accessToken.length() < 8){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }
        String tokenType = accessToken.substring(0,6);
        String tokenStr = accessToken.substring(7);

        //TODO : 이부분 고려!
        if(tokenType.equalsIgnoreCase("Bearer")==false){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        try{
            Map<String,Object> values = jwtUtil.validateToken(tokenStr);

            return values;
        }catch (MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException------------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        }catch (SignatureException signatureException){
            log.error("SignatureException---------------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        }catch (ExpiredJwtException expiredJwtException){
            log.error("ExpiredJwtException--------------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }

}
