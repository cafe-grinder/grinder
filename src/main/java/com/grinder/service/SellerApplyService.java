package com.grinder.service;

import com.grinder.domain.dto.SellerApplyDTO;
import com.grinder.domain.entity.SellerApply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SellerApplyService {

    List<SellerApplyDTO.FindSellerApplyDTO> findAllSellerApplies();

    void deleteSellerApply(String applyId);

    void saveSellerApply(String memberId, String cafeId, MultipartFile file);

    SellerApply findSellerApply(String applyId);
}
