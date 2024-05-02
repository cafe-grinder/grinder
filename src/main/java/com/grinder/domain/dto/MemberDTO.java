package com.grinder.domain.dto;

import com.grinder.domain.entity.Member;
import lombok.Getter;
import lombok.Setter;

public class MemberDTO {

    @Getter
    @Setter
    public static class FindMemberDTO {

        private String memberId;
        private String email;
        private String nickname;
        private String phoneNum;
        private String role;
        private Boolean isDeleted;

        public FindMemberDTO(Member member) {
            this.memberId = member.getMemberId();
            this.email = member.getEmail();
            this.nickname = member.getNickname();
            this.phoneNum = member.getPhoneNum();
            this.role = member.getRole().getValue();
            this.isDeleted = member.getIsDeleted();
        }
    }

}
