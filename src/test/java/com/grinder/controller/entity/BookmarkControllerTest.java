package com.grinder.controller.entity;

import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.service.implement.BookmarkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookmarkControllerTest {
    @InjectMocks
    private BookmarkController bookmarkController;
    @Mock
    private BookmarkServiceImpl bookmarkService;
    @Mock
    private Authentication authentication;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookmarkController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("test@test.com", "password", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("test@test.com");
    }

    @Test
    void findAllBookmarksSlice() throws Exception {
        List<BookmarkDTO.findAllResponse> expectedResponse = new ArrayList<>();
        BookmarkDTO.findAllResponse thing = new BookmarkDTO.findAllResponse();
        thing.setCafeName("스타벅스");
        expectedResponse.add(thing);

        Mockito.doReturn(expectedResponse)
                .when(bookmarkService).findAllBookmarksSlice(any(String.class), any(Pageable.class));

        ResultActions resultActions =mockMvc.perform(get("/api/bookmark")
                .param("page", "0")
                .param("size", "10")
                .principal(authentication));

        resultActions.andExpect(status().isOk()).andDo(print());
    }
}