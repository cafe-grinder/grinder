package com.grinder.controller.entity;

import com.grinder.domain.dto.FollowDTO;
import com.grinder.service.implement.FollowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FollowControllerTest {
    @InjectMocks
    private FollowController followController;
    @Mock
    private FollowServiceImpl followService;
    @Mock
    private Authentication authentication;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(followController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("test@test.com", "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("test@test.com");
    }

    @Test
    void findAllFollowingSlice() throws Exception {
        List<FollowDTO.findAllFollowingResponse> expectedResponse = new ArrayList<>();
        FollowDTO.findAllFollowingResponse thing = new FollowDTO.findAllFollowingResponse();
        thing.setFollowingEmail("test2@test.com");
        expectedResponse.add(thing);

        Mockito.doReturn(expectedResponse)
                .when(followService).findAllFollowingSlice(any(String.class), any(Pageable.class));

        ResultActions resultActions =mockMvc.perform(get("/api/following")
                .param("page", "0")
                .param("size", "10")
                .principal(authentication));

        resultActions.andExpect(status().isOk()).andDo(print());
    }
}