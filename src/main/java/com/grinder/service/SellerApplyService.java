package com.grinder.service;

import com.grinder.domain.dto.SellerApplyDTO;

import java.util.List;

public interface SellerApplyService {

    List<SellerApplyDTO.FindSellerApplyDTO> findAllSellerApplies();

    void deleteSellerApply(String applyId);
}
