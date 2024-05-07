package com.grinder.service;

import com.grinder.domain.entity.SellerInfo;

public interface SellerInfoService {

    void saveSellerInfo(String applyId);

    SellerInfo findSellerInfoById(Long sellerInfoId);

    void deleteSellerInfo(Long sellerInfoId);
}
