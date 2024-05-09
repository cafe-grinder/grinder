package com.grinder.service.implement;

import static com.grinder.domain.dto.CafeRegisterDTO.*;
import com.grinder.domain.entity.CafeRegister;
import com.grinder.domain.entity.Member;
import com.grinder.repository.CafeRegisterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static com.grinder.domain.dto.CafeRegisterDTO.*;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CafeRegisterServiceImplTest {

    @InjectMocks
    CafeRegisterServiceImpl cafeRegisterService;

    @Mock
    CafeRegisterRepository cafeRegisterRepository;

    @Mock
    Pageable pageable;

    @DisplayName("신규 장소 등록 내역 조회")
    @Test
    void testFindAllCafeRegister() {
        //given
        List<CafeRegister> cafeRegisterList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cafeRegisterList.add(CafeRegister.builder().member(new Member()).build());
        }


        doReturn(new SliceImpl<>(cafeRegisterList, pageable, false)).when(cafeRegisterRepository).findAll(any(Pageable.class));

        //when
        Slice<FindCafeRegisterDTO> cafeRegisterDTOSlice = cafeRegisterService.FindAllCafeRegisters(pageable);

        //then
        assertThat(cafeRegisterDTOSlice.getContent().size()).isEqualTo(3);
    }

}