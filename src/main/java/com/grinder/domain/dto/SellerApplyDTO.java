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
        private String nickname;
        private String cafeId;
        private String cafeName;
        private String phoneNum;
        private String regImageUrl;

        public FindSellerApplyDTO(SellerApply apply) {
            this.applyId = apply.getApplyId();
            this.memberId = apply.getMember().getMemberId();
            this.nickname = apply.getMember().getNickname();
            this.cafeId = apply.getCafe().getCafeId();
            this.cafeName = apply.getCafe().getName();
            this.phoneNum = apply.getCafe().getPhoneNum().substring(0,3) + "-" + apply.getCafe().getPhoneNum().substring(3,7) + "-" + apply.getCafe().getPhoneNum().substring(7,11);
            this.regImageUrl = apply.getRegImageUrl();
        }
    }
}
