package com.grinder.domain.dto;

import com.grinder.domain.entity.SellerApply;
import lombok.Getter;
import lombok.Setter;

public class SellerApplyDTO {

    @Getter
    @Setter
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
