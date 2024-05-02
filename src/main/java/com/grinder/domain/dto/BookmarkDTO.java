package com.grinder.domain.dto;

import com.grinder.domain.entity.Bookmark;
import lombok.Getter;
import lombok.Setter;

public class BookmarkDTO {

    @Getter
    @Setter
    public static class findAllResponse {
        private String cafeId;
        private String cafeName;
        private String cafeAddress;
        private String CafePhoneNum;
        private Integer averageGrade;
        private String cafeImageUrl;

        public findAllResponse() {
        }

        public findAllResponse(Bookmark bookmark, String imageUrl) {
            cafeId = bookmark.getCafe().getCafeId();
            cafeName = bookmark.getCafe().getName();
            cafeAddress = bookmark.getCafe().getAddress();
            CafePhoneNum = bookmark.getCafe().getPhoneNum();
            averageGrade = bookmark.getCafe().getAverageGrade();
            if (imageUrl == null)  imageUrl = "";
            this.cafeImageUrl = imageUrl;
        }
    }
}
