package com.grinder.service;

import com.grinder.domain.dto.SellerInfoDTO;
import com.grinder.domain.entity.SellerInfo;

import java.util.List;

public interface SellerInfoService {

    void saveSellerInfo(String applyId);

    SellerInfo findSellerInfoById(Long sellerInfoId);

    void deleteSellerInfo(Long sellerInfoId);

    List<SellerInfoDTO.findAllResponse> findAllSellerInfoByEmail(String sellerEmail);
}
