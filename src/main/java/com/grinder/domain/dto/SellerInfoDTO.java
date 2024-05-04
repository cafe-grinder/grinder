package com.grinder.domain.dto;

import com.grinder.domain.entity.SellerInfo;
import lombok.Getter;
import lombok.Setter;

public class SellerInfoDTO {

    @Getter
    @Setter
    public static class FindSellerInfoDTO {
        private Long sellerInfoId;
        private String memberId;
        private String cafeId;

        public FindSellerInfoDTO(SellerInfo sellerInfo) {
            this.sellerInfoId = sellerInfo.getSellerInfoId();
            this.memberId = sellerInfo.getMember().getMemberId();
            this.cafeId = sellerInfo.getCafe().getCafeId();
        }
    }
}
