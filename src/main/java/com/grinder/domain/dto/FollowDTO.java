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
        private String followingNickname;
        private String followingEmail;
        private String followingRole;
        private String followImage;

        public findAllFollowingResponse(Follow follow, String imageUrl) {
            followId = follow.getFollowId();
            followingNickname = follow.getFollowing().getNickname();
            followingEmail = follow.getFollowing().getEmail();
            followingRole = follow.getFollowing().getRole().getValue();
            if (imageUrl == null) imageUrl = "";
            followImage = imageUrl;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class findAllFollowerResponse {
        private Long followId;
        private String followerNickname;
        private String followerEmail;
        private String followerRole;
        private String followerImage;

        public findAllFollowerResponse(Follow follow, String imageUrl) {
            followId = follow.getFollowId();
            followerNickname = follow.getMember().getNickname();
            followerEmail = follow.getMember().getEmail();
            followerRole = follow.getMember().getRole().getValue();
            if (imageUrl == null) imageUrl = "";
            followerImage = imageUrl;
        }
    }
}
