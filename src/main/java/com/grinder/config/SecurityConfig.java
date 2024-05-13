package com.grinder.config;

import com.grinder.repository.MemberRepository;
import com.grinder.repository.RefreshRepository;
import com.grinder.security.service.CustomOAuth2MemberService;
import com.grinder.security.service.MemberDetailsService;
import com.grinder.security.filter.APILoginFilter;
import com.grinder.security.filter.APILogoutFilter;
import com.grinder.security.filter.RefreshTokenFilter;
import com.grinder.security.filter.TokenCheckFilter;
import com.grinder.security.handler.APILoginFailureHandler;
import com.grinder.security.handler.APILoginSuccessHandler;
import com.grinder.utils.JWTUtil;
import com.grinder.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberDetailsService memberDetailsService;
    private final MemberRepository memberRepository;
    private final RefreshRepository refreshRepository;
    private final JWTUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final CustomOAuth2MemberService customOAuth2MemberService;


    @Bean
    public WebSecurityCustomizer configure() {      // 1) 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring()
            // .requestMatchers(toH2Console())
                .requestMatchers("/static/**", "/img/**", "/js/**", "/css/**", "/fonts/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        APILoginSuccessHandler successHandler = new APILoginSuccessHandler(jwtUtil,refreshRepository);
        APILoginFailureHandler failureHandler = new APILoginFailureHandler();
        //AuthenticationManager 설정
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // 인증 유저 관련
        authenticationManagerBuilder
                .userDetailsService(memberDetailsService)
                .passwordEncoder(passwordEncoder());
        // AuthenticationManager 빌드
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        // 설정 저장 필수
        http.authenticationManager(authenticationManager);
        //oauth2
        http.oauth2Login((oauth2)-> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2MemberService))
                .successHandler(successHandler)
                .failureHandler(failureHandler));

        //APILOGINFilter
        APILoginFilter apiLoginFilter = new APILoginFilter("/api/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager);

        //APILoginFilter의 위치 조정
        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
        // 인증 후처리 담당
        apiLoginFilter.setAuthenticationSuccessHandler(successHandler);
        apiLoginFilter.setAuthenticationFailureHandler(failureHandler);

        http.addFilterBefore(
                tokenCheckFilter(jwtUtil),
                UsernamePasswordAuthenticationFilter.class
        );
        http.addFilterBefore(new RefreshTokenFilter("/api/reissue",jwtUtil, refreshRepository, redisUtil),
                TokenCheckFilter.class);

        http.addFilterBefore(new APILogoutFilter(jwtUtil, refreshRepository,redisUtil), LogoutFilter.class);
        http.csrf(csrf->csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->              // 인증, 인가 설정
                        auth.requestMatchers("/").permitAll() //TODO: url 추가
//                                .anyRequest().authenticated())
//                                .requestMatchers("/api/admin/").hasRole("관리자")
                                .anyRequest().permitAll());
        http.logout(log -> log.deleteCookies());
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil){
        return new TokenCheckFilter(jwtUtil,memberDetailsService);
    }
}
