package com.grinder.domain.dto;

import com.grinder.domain.entity.CafeRegister;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeRegisterDTO {

    @Getter
    @Setter
    public static class FindCafeRegisterDTO {

        private String registerId;
        private String memberId;
        private String name;
        private String address;

        public FindCafeRegisterDTO(CafeRegister cafeRegister) {
            this.registerId = cafeRegister.getRegisterId();
            this.memberId = cafeRegister.getMember().getMemberId();
            this.name = cafeRegister.getName();
            this.address = cafeRegister.getAddress();
        }
    }

    @Getter
    @Setter
    public static class SaveCafeRegisterDTO {

        private String registerId;
        private String memberId;
        private String name;
        private String address;
        private String phoneNum;

        public SaveCafeRegisterDTO(CafeRegister cafeRegister) {
            this.registerId = cafeRegister.getRegisterId();
            this.memberId = cafeRegister.getMember().getMemberId();
            this.name = cafeRegister.getName();
            this.address = cafeRegister.getAddress();
            this.phoneNum = cafeRegister.getPhoneNum();
        }
    }
}
