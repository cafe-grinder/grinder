package com.grinder.domain.dto;

import com.grinder.domain.entity.SellerApply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SellerApplyDTO {

    @Getter
    @Setter
    public static class FindSellerApplyDTO {
        private String applyId;
        private String memberId;
        private String cafeId;
        private String regImageUrl;

        public FindSellerApplyDTO(SellerApply apply) {
            this.applyId = apply.getApplyId();
            this.memberId = apply.getMember().getMemberId();
            this.cafeId = apply.getCafe().getCafeId();
            this.regImageUrl = apply.getRegImageUrl();
        }
    }
}
