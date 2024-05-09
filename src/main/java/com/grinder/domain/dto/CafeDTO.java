package com.grinder.domain.dto;

import com.grinder.domain.entity.Cafe;
import lombok.Getter;
import lombok.Setter;

public class CafeDTO {

    @Getter
    @Setter
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
}
