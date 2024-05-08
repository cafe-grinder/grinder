package com.grinder.controller.view;

import com.grinder.domain.dto.*;
import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Tag;
import com.grinder.domain.enums.ContentType;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ComponentsController {
    private final MemberService memberService;
    private final FollowService followService;
    private final BlacklistService blacklistService;
    private final FeedService feedService;
    private final CommentService commentService;
    private final HeartService heartService;
    private final TagService tagService;

    @GetMapping("/get-header")
    public String getHeader(Authentication authentication, Model model) {
        MemberDTO.FindMemberDTO member = null;
        try {
            String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
            member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        } catch (Exception e) {}
        model.addAttribute("headerMember",member);
        return "components/header :: headers";
    }

    @GetMapping("/get-follower")
    public String getFollower(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<FollowDTO.findAllFollowerResponse> list = new ArrayList<>();
        email = "test@test.com";
        if (email != null) {
            if (!email.equals("anonymousUser"))
                list = followService.findAllFollowerSlice(email, pageable);
        }
        model.addAttribute("followMembers", list);
        return "components/followerList :: followList(title='팔로워보기')";
    }

    @GetMapping("/get-following")
    public String getFollowing(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<FollowDTO.findAllFollowingResponse> list = new ArrayList<>();
        email = "test@test.com";
        if (email != null && !email.equals("anonymousUser")) {
            list = followService.findAllFollowingSlice(email, pageable);
        }
        model.addAttribute("followMembers", list);
        return "components/followerList :: followList(title='팔로잉보기')";
    }

    @GetMapping("/get-blacklist")
    public String getBlacklist(Model model) {
        String email = getEmail();
        List<BlacklistDTO.findAllResponse> list = new ArrayList<>();
        email = "test@test.com";
        if (email != null && !email.equals("anonymousUser")) {
            list = blacklistService.findAllBlacklist(email);
        }
        model.addAttribute("blackMembers", list);
        return "components/blacklist :: blackList(title='차단목록 보기')";
    }

    @GetMapping("/get-feed")
    public String getFeed(Model model) {
        // 멤버
        String email = "example1@example.com"; // TODO: 테스트용. 나중에 수정하기!
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("feedMember", member);

        // 피드
        List<Feed> feedList = feedService.findAllFeed();
        List<FeedDTO.FeedResponseDTO> feedResponseList = feedList.stream().map(FeedDTO.FeedResponseDTO::new).toList();
        for (FeedDTO.FeedResponseDTO feedResponse : feedResponseList) {
            // 태그
            List<String> tagList = tagService.findAllTag(feedResponse.getFeedId()).stream().map(x->x.getTagName().getValue()).toList();
            feedResponse.setTagNameList(tagList);

            // 피드 좋아요
            HeartDTO.HeartRequestDTO feedHeartRequest = new HeartDTO.HeartRequestDTO(feedResponse.getFeedId(), ContentType.FEED.toString());
            feedResponse.setHeart(heartService.isHeart(email, feedHeartRequest));
            feedResponse.setHeartNum(heartService.findHeartList(feedHeartRequest).size());

            // 부모 댓글
            List<Comment> parentCommentList = commentService.findParentCommentList(feedResponse.getFeedId());
            List<CommentDTO.ParentCommentResponseDTO> parentCommentResponseList = parentCommentList.stream().map(CommentDTO.ParentCommentResponseDTO::new).toList();

            for (CommentDTO.ParentCommentResponseDTO parentCommentResponse : parentCommentResponseList) {
                // 부모 댓글 좋아요
                HeartDTO.HeartRequestDTO parentCommentHeartRequest = new HeartDTO.HeartRequestDTO(parentCommentResponse.getCommentId(), ContentType.COMMENT.toString());
                parentCommentResponse.setHeart(heartService.isHeart(email, parentCommentHeartRequest));
                parentCommentResponse.setHeartNum(heartService.findHeartList(parentCommentHeartRequest).size());

                // 자식 댓글
                List<Comment> childCommentList = commentService.findChildrenCommentList(parentCommentResponse.getCommentId());
                List<CommentDTO.ChildCommentResponseDTO> childCommentResponseList = childCommentList.stream().map(CommentDTO.ChildCommentResponseDTO::new).toList();

                for (CommentDTO.ChildCommentResponseDTO childCommentResponse : childCommentResponseList) {
                    // 자식 댓글 좋아요
                    HeartDTO.HeartRequestDTO childCommentHeartRequest = new HeartDTO.HeartRequestDTO(childCommentResponse.getCommentId(), ContentType.COMMENT.toString());
                    childCommentResponse.setHeart(heartService.isHeart(email, childCommentHeartRequest));
                    childCommentResponse.setHeartNum(heartService.findHeartList(childCommentHeartRequest).size());
                }
                parentCommentResponse.setChildCommentList(childCommentResponseList);
            }
            feedResponse.setParentCommentList(parentCommentResponseList);
        }
        model.addAttribute("feedList", feedResponseList);

        return "components/feed";
    }

    @GetMapping("/get-cafeCard")
    public String getCafeCard() {
        return "components/cafeCard";
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
