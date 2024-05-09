package com.grinder.domain.dto;

import com.grinder.domain.entity.Blacklist;
import lombok.Getter;
import lombok.Setter;

public class BlacklistDTO {

    @Getter
    @Setter
    /**
     * memberId : 본인
     * blockedMemberId : 상대
     */
    public static class AddRequest {
        private String memberEmail;
        private String blockedMemberEmail;
    }

    @Getter
    @Setter
    public static class findAllResponse {
        private Long blockedId;
        private String blockedImageUrl;
        private String blockedNickname;
        private String blockedEmail;
        private String blockedRole;

        public findAllResponse(){}
        public findAllResponse(Blacklist blacklist, String ImageUrl) {
            blockedId = blacklist.getBlacklistId();
            blockedImageUrl = ImageUrl;
            blockedNickname = blacklist.getBlockedMember().getNickname();
            blockedEmail = blacklist.getBlockedMember().getEmail();
            blockedRole = blacklist.getBlockedMember().getRole().getValue();
        }
    }
}
