package com.grinder.service.implement;

import com.grinder.domain.dto.SellerInfoDTO;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.SellerApply;
import com.grinder.domain.entity.SellerInfo;
import com.grinder.repository.CafeRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.SellerInfoRepository;
import com.grinder.repository.queries.ImageQueryRepository;
import com.grinder.repository.queries.SellerInfoQueryRepository;
import com.grinder.service.CafeService;
import com.grinder.service.MemberService;
import com.grinder.service.SellerApplyService;
import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerInfoServiceImpl implements SellerInfoService {

    private final SellerInfoRepository sellerInfoRepository;
    private final CafeService cafeService;
    private final SellerApplyService sellerApplyService;
    private final SellerInfoQueryRepository sellerInfoQueryRepository;
    private final CafeRepository cafeRepository;
    private final MemberRepository memberRepository;
    private final ImageQueryRepository imageQueryRepository;

    @Override
    @Transactional
    public void saveSellerInfo(String applyId) {
        SellerApply sellerApply = sellerApplyService.findSellerApply(applyId);
        sellerInfoRepository.save(SellerInfo
                .builder()
                .cafe(sellerApply.getCafe())
                .member(sellerApply.getMember())
                .build());
        Cafe cafe = cafeService.findCafeById(sellerApply.getCafe().getCafeId());
        if (cafe.getRegImageUrl() != null) {
            cafe.uploadRegImage(sellerApply.getRegImageUrl());
        }
        sellerApplyService.deleteSellerApply(applyId);
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

    @Override
    public List<SellerInfoDTO.findAllResponse> findAllSellerInfoByEmail(String sellerEmail) {
        return sellerInfoQueryRepository.findAllSellerInfo(sellerEmail);
    }

    @Override
    public boolean existByMemberAndCafe(String cafeId, String memberEmail) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(() -> new NoSuchElementException("카페 정보가 존재하지 않습니다."));
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new NoSuchElementException("카페 정보가 존재하지 않습니다."));
        return sellerInfoRepository.existsByMemberAndCafe(member, cafe);
    }

}
