package com.grinder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SellerInfoDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveSellerInfoDTO {
        private String memberId;
        private String cafeId;
    }
}
