package com.grinder.controller.view;

import com.grinder.domain.dto.*;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Tag;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.MenuType;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ComponentsController {
    private final MemberService memberService;
    private final FollowService followService;
    private final BlacklistService blacklistService;
    private final BookmarkService bookmarkService;
    private final SellerInfoService sellerInfoService;
    private final FeedService feedService;
    private final CommentService commentService;
    private final HeartService heartService;
    private final TagService tagService;
    private final MyMenuService myMenuService;
    private final CafeService cafeService;

    @GetMapping("/get-header")
    public String getHeader(Model model, HttpServletRequest request, HttpServletResponse response) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = null;
        if (email != null && !email.equals("anonymousUser")) {
            member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        }
        model.addAttribute("headerMember",member);
        return "components/header :: headers";
    }

    @GetMapping("/get-follower")
    public String getFollower(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<FollowDTO.findAllFollowerResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
                list = followService.findAllFollowerSlice(email, pageable).getContent();
        }
        model.addAttribute("followMembers", list);
        return "components/followerList :: followList(title='follower')";
    }

    @GetMapping("/get-following")
    public String getFollowing(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<FollowDTO.findAllFollowingResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
            list = followService.findAllFollowingSlice(email, pageable).getContent();
        }
        model.addAttribute("followMembers", list);
        return "components/followerList :: followList(title='following')";
    }

    @GetMapping("/get-blacklist")
    public String getBlacklist(Model model) {
        String email = getEmail();
        List<BlacklistDTO.findAllResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
            list = blacklistService.findAllBlacklist(email);
        }
        model.addAttribute("blackMembers", list);
        return "components/blacklist :: blackList";
    }

    @GetMapping("/get-bookmark")
    public String getBookmark(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<BookmarkDTO.findAllResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
            list = bookmarkService.findAllBookmarksSlice(email, pageable).getContent();
        }
        model.addAttribute("bookmarks", list);
        return "components/bookmark :: bookmarkList";
    }

    @GetMapping("/get-mycafe")
    public String getMyCafe(Model model) {
        String email = getEmail();
        List<SellerInfoDTO.findAllResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
            list = sellerInfoService.findAllSellerInfoByEmail(email);
        }
        model.addAttribute("myCafeList", list);
        return "components/myCafeList :: myCafeList";
    }

    @GetMapping("/get-opening")
    public String getOpening() {
        return "components/updateCafeInfo :: updateCafeInfo";
    }

    @GetMapping("/get-mymenu/{cafe_id}")
    public String getMyMenu(@PathVariable("cafe_id")String cafeId, Model model) {
        String email = getEmail();
        List<MenuDTO.findAllMenuResponse> list = new ArrayList<>();
        if (email != null && !email.equals("anonymousUser")) {
            list = myMenuService.findAllMenuWithImage(email,cafeId);
        }
        model.addAttribute("myMenus", list);
        return "components/myCafeMenu :: myCafeMenu";
    }
    @GetMapping("/get-addpage")
    public String addMenuTab(Model model) {
        List<String> menuType = new ArrayList<>();
        for (MenuType typeName : MenuType.values()) {
            menuType.add(typeName.getValue());
        }
        model.addAttribute("typeList", menuType);
        return "components/menuInfo :: addMenu";
    }

    @GetMapping("/get-feed")
    public String getFeed(Model model) {
        // 멤버
        String email = "test@test.com"; // TODO: 테스트용. 나중에 수정하기!
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
    public String getCafeCard(@RequestParam String query, @PageableDefault(size = 6) Pageable pageable ,Model model) {
        Slice<CafeDTO.findAllWithImageAndTagResponse> cafeSlice =  cafeService.searchCafes(query, pageable);
        model.addAttribute("cafeSlice", cafeSlice);
        return "components/cafeCard";
    }

    @GetMapping("/get-search-feed")
    public String getSearchFeed(@RequestParam String query, @PageableDefault Pageable pageable, Authentication authentication, Model model) {
        UserDetails loginMember =  (UserDetails)authentication.getPrincipal();
        String email = loginMember.getUsername();
        Slice<FeedDTO.FeedWithImageResponseDTO> feedSlice = feedService.searchFeed(email, query, pageable);
        model.addAttribute("feedSlice", feedSlice);
        return "components/feed";
    }

    @GetMapping("/get-myFeed/{email}")
    public String getMyFeed(@PathVariable("email")String memberEmail,
                            @PageableDefault Pageable pageable,
                            Model model) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("feedMember", member);
        Slice<FeedDTO.FeedWithImageResponseDTO> slice = feedService.findMyPageFeedWithImage(email, memberEmail, pageable);
        model.addAttribute("hasNext", slice.hasNext());
        model.addAttribute("feedList", slice.getContent());
        return "components/feed";
    }

    @GetMapping("/get-cafeFeed/{cafeId}")
    public String getCafeFeed(@PathVariable("cafeId")String cafeId,
                            @PageableDefault Pageable pageable,
                            Model model) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("feedMember", member);
        Slice<FeedDTO.FeedWithImageResponseDTO> slice = feedService.findCafeFeedWithImage(email, cafeId, pageable);
        model.addAttribute("hasNext", slice.hasNext());
        model.addAttribute("feedList", slice.getContent());
        return "components/feed";
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
