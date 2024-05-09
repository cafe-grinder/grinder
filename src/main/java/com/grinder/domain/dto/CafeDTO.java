package com.grinder.domain.dto;

import com.grinder.domain.entity.Cafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CafeDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CafeSearchByAdminDTO{
        private String name;
        private String address;
        private String phoneNum;

        public CafeSearchByAdminDTO(Cafe cafe) {
            this.name = cafe.getName();
            this.address = cafe.getAddress();
            this.phoneNum = cafe.getPhoneNum();
            }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class findAllWithImageResponse {
        private String cafeId;
        private String cafeName;
        private String cafeAddress;
        private String cafePhoneNum;
        private Integer averageGrade;
        private String cafeImageUrl;


        public findAllWithImageResponse(Cafe cafe, String imageUrl) {
            cafeId = cafe.getCafeId();
            cafeName = cafe.getName();
            cafeAddress = cafe.getAddress();
            cafePhoneNum = cafe.getPhoneNum();
            averageGrade = cafe.getAverageGrade();
            cafeImageUrl = imageUrl;
        }
    }
}
