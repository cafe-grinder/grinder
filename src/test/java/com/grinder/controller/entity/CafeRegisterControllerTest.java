package com.grinder.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grinder.domain.entity.CafeRegister;
import com.grinder.domain.entity.Member;
import com.grinder.service.implement.CafeRegisterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static com.grinder.domain.dto.CafeRegisterDTO.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CafeRegisterControllerTest {

    @InjectMocks
    CafeRegisterController cafeRegisterController;

    @Mock
    CafeRegisterServiceImpl cafeRegisterService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(cafeRegisterController).build();
    }

    @DisplayName("신규 장소 등록신청 조회")
    @Test
    void testFindAllCafeRegisters() throws Exception{
        List<FindCafeRegisterDTO> registerDTOList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            registerDTOList.add(new FindCafeRegisterDTO(CafeRegister.builder().member(new Member()).build()));
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedResponse = objectMapper.writeValueAsString(registerDTOList);

        doReturn(registerDTOList).when(cafeRegisterService).FindAllCafeRegisters();


        mockMvc.perform(get("/api/cafe_register/find"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}