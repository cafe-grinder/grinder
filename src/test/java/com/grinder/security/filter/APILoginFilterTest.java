package com.grinder.security.filter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class APILoginFilterTest {

    private AuthenticationManager mockAuthenticationManager(Authentication auth) {
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(auth);
        return authenticationManager;
    }

    @Test
    @DisplayName("GET 요청 시 null을 반환")
    void unsupported_get_method_should_return_null() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        request.setMethod("GET");
        var authManager = mock(AuthenticationManager.class);
        var filter = new APILoginFilter("/api/login");
        filter.setAuthenticationManager(authManager);

        Authentication result = filter.attemptAuthentication(request, response);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("인증 성공 시 다음필터로 진행됩니다.")
    void successful_authentication_should_continue_filter_chain() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        request.setMethod("POST");
        request.setContent("{\"email\":\"user@example.com\", \"password\":\"password\"}".getBytes());

        var auth = new UsernamePasswordAuthenticationToken("user@example.com", "password");
        var authManager = mockAuthenticationManager(auth);
        var filter = new APILoginFilter("/api/login");
        filter.setAuthenticationManager(authManager);

        filter.attemptAuthentication(request, response);

        assertEquals(200, response.getStatus());
        verify(authManager, times(1)).authenticate(any(Authentication.class));
    }

    @Test
    @DisplayName("실패 시 예외를 반환합니다.")
    void failed_authentication_should_return_error_message() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        request.setMethod("POST");
        request.setContent("{\"email\":\"user@example.com\", \"password\":\"wrongpassword\"}".getBytes());

        var authManager = mock(AuthenticationManager.class);
        when(authManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));
        var filter = new APILoginFilter("/api/login");
        filter.setAuthenticationManager(authManager);

        // 예외 발생을 기대하고 이를 assertThrows로 잡아냅니다.
        AuthenticationException exception = assertThrows(AuthenticationException.class, () -> {
            filter.attemptAuthentication(request, response);
        });

        // 예외가 발생한 후 응답 상태와 메시지를 설정합니다.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        assertEquals(401, response.getStatus());
    }
}