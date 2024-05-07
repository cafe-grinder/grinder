package com.grinder.service.implement;

import com.grinder.domain.dto.SellerInfoDTO;
import com.grinder.domain.entity.SellerInfo;
import com.grinder.repository.SellerInfoRepository;
import com.grinder.service.CafeService;
import com.grinder.service.MemberService;
import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerInfoServiceImpl implements SellerInfoService {

    private final SellerInfoRepository sellerInfoRepository;
    private final MemberService memberService;
    private final CafeService cafeService;

    @Override
    public void saveSellerInfo(SellerInfoDTO.SaveSellerInfoDTO sellerInfoDTO) {
        sellerInfoRepository.save(SellerInfo
                .builder()
                        .member(memberService.findMemberById(sellerInfoDTO.getMemberId()))
                .cafe(cafeService.findCafeById(sellerInfoDTO.getCafeId()))
                .build());
    }

    @Override
    public SellerInfo findSellerInfoById(Long sellerInfoId) {
        return sellerInfoRepository.findById(sellerInfoId).orElseThrow(() -> new NoSuchElementException("판매자 정보가 존재하지 않습니다."));
    }

    @Override
    @Transactional
    public void deleteSellerInfo(Long sellerInfoId) {
        SellerInfo sellerInfo = findSellerInfoById(sellerInfoId);
        sellerInfoRepository.delete(sellerInfo);
    }
}
