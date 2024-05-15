package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller_info")
public class SellerInfoController {

    private final SellerInfoService sellerInfoService;

    @DeleteMapping("/{seller_info_id}")
    public ResponseEntity<SuccessResult> deleteSellerInfo(@PathVariable Long seller_info_id) {
        sellerInfoService.deleteSellerInfo(seller_info_id);
        return ResponseEntity.ok(new SuccessResult("Delete seller_info", "판매자 정보가 삭제되었습니다."));
    }
}
