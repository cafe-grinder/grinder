package com.grinder.service;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.SellerApply;
import com.grinder.repository.SellerApplyRepository;
import com.grinder.service.implement.SellerApplyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static com.grinder.domain.dto.SellerApplyDTO.*;

@ExtendWith(MockitoExtension.class)
class SellerApplyServiceTest {

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
        List<FindSellerApplyDTO> sellerApplyDTOList = sellerApplyService.findAllSellerApplies();

        //then
        assertThat(sellerApplyDTOList.size()).isEqualTo(3);
    }

}