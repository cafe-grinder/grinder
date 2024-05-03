package com.grinder.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grinder.controller.MemberController;
import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.Role;
import com.grinder.repository.MemberRepository;
import com.grinder.service.implement.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks
    MemberController memberController;

    @Mock
    MemberServiceImpl memberService;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @DisplayName("전체 회원 조회 테스트")
    @Test
    void testFindAllMembers() throws Exception {
        //given
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            memberList.add(Member.builder().role(Role.MEMBER).build());
        }

        doReturn(memberList).when(memberService).findAllMembers();

        //when
        ResultActions actions = mockMvc.perform(get("/api/member/find"));

        //then
        actions.andExpect(model().attributeExists("memberList"));
    }


}