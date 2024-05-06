package com.grinder.service.implement;

import com.grinder.domain.dto.SellerApplyDTO;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.SellerApply;
import com.grinder.repository.SellerApplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SellerApplyServiceImplTest {
    @InjectMocks
    SellerApplyServiceImpl sellerApplyService;

    @Mock
    SellerApplyRepository sellerApplyRepository;

    @DisplayName("판매자 신청 내역 조회")
    @Test
    void testFindAllSellerApplies() {
        //given
        List<SellerApply> sellerApplyList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            sellerApplyList.add(SellerApply.builder().member(new Member()).cafe(new Cafe()).build());
        }

        doReturn(sellerApplyList).when(sellerApplyRepository).findAll();

        //when
        List<SellerApplyDTO.FindSellerApplyDTO> sellerApplyDTOList = sellerApplyService.findAllSellerApplies();

        //then
        assertThat(sellerApplyDTOList.size()).isEqualTo(3);
    }

    @DisplayName("판매자 신청 내역 삭제")
    @Test
    void testDeleteSellerApply() {
        SellerApply apply = SellerApply.builder().applyId(UUID.randomUUID().toString()).member(new Member()).cafe(new Cafe()).build();

        doNothing().when(sellerApplyRepository).deleteById(apply.getApplyId());

        sellerApplyService.deleteSellerApply(apply.getApplyId());

        verify(sellerApplyRepository,times(1)).deleteById(apply.getApplyId());
    }
}