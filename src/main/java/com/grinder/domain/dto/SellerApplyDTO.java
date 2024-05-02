package com.grinder.domain.dto;

import com.grinder.domain.entity.SellerApply;

public class SellerApplyDTO {

    public static class FindSellerApplyDTO {
        private String applyId;
        private String memberId;
        private String cafeId;
        private String imageUrl;

        public FindSellerApplyDTO(SellerApply apply) {
            this.applyId = apply.getApplyId();
            this.memberId = apply.getMember().getMemberId();
            this.cafeId = apply.getCafe().getCafeId();
            this.imageUrl = apply.getImageUrl();
        }
    }
}
