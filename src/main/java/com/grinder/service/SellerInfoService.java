package com.grinder.service;

import com.grinder.domain.dto.SellerInfoDTO;
import com.grinder.domain.entity.SellerInfo;

public interface SellerInfoService {

    void saveSellerInfo(SellerInfoDTO.SaveSellerInfoDTO sellerInfoDTO);

    SellerInfo findSellerInfoById(Long sellerInfoId);

    void deleteSellerInfo(Long sellerInfoId);
}
