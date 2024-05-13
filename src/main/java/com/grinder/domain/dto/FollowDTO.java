package com.grinder.domain.dto;

import com.grinder.domain.entity.Follow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FollowDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class findAllFollowingResponse {
        private Long followId;
        private String followMemberId;
        private String followNickname;
        private String followEmail;
        private String followRole;
        private String followImage;

        public findAllFollowingResponse(Follow follow, String imageUrl) {
            followId = follow.getFollowId();
            followMemberId = follow.getFollowing().getMemberId();
            followNickname = follow.getFollowing().getNickname();
            followEmail = follow.getFollowing().getEmail();
            followRole = follow.getFollowing().getRole().getValue();
            followImage = imageUrl;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class findAllFollowerResponse {
        private Long followId;
        private String followMemberId;
        private String followNickname;
        private String followEmail;
        private String followRole;
        private String followImage;

        public findAllFollowerResponse(Follow follow, String imageUrl) {
            followId = follow.getFollowId();
            followMemberId = follow.getMember().getMemberId();
            followNickname = follow.getMember().getNickname();
            followEmail = follow.getMember().getEmail();
            followRole = follow.getMember().getRole().getValue();
            followImage = imageUrl;
        }
    }
}
