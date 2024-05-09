package com.grinder.domain.dto;

import com.grinder.domain.entity.Cafe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CafeDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CafeRequestDTO {
        private String cafeId;
        private String name;
        private String address;
        private String phoneNum;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CafeResponseDTO {
        String cafeId;
        String name;
        String address;
        String phoneNum;
        Integer averageGrade;
    }

//    @Data
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class FeedAndImageResponseDTO {
//
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class FindCafeDTO {
//
//    }

    @Getter
    @Setter
    public static class findAllWithImageResponse {
        private String cafeId;
        private String cafeName;
        private String cafeAddress;
        private String CafePhoneNum;
        private Integer averageGrade;
        private String cafeImageUrl;

        public findAllWithImageResponse() {
        }

        public findAllWithImageResponse(Cafe cafe, String imageUrl) {
            cafeId = cafe.getCafeId();
            cafeName = cafe.getName();
            cafeAddress = cafe.getAddress();
            CafePhoneNum = cafe.getPhoneNum();
            averageGrade = cafe.getAverageGrade();
            cafeImageUrl = imageUrl;
        }
    }
}
