package com.grinder.controller.view;

import com.grinder.domain.dto.*;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.MenuType;
import com.grinder.domain.enums.TagName;
import com.grinder.exception.LoginRequiredException;
import com.grinder.exception.NoMoreContentException;
import com.grinder.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
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
    private final MessageService messageService;
    private final AnalysisTagService analysisTagService;
    private final CafeService cafeService;


    @GetMapping("/get-header")
    public String getHeader(Model model, HttpServletRequest request, HttpServletResponse response) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = null;
        boolean checkMessage = false;
        if (email != null && !email.equals("anonymousUser")) {
            member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
            checkMessage = messageService.existNonCheckMessage(email);
        }
        List<Message> message = messageService.findAllByEmail(email);
        model.addAttribute("AlanMessages", message);
        model.addAttribute("headerMember",member);
        model.addAttribute("checkMessage", checkMessage);
        return "components/header :: headers";
    }

    @GetMapping("/get-alan")
    public String getAlanMessage(Model model) {
        String email = getEmail();
        List<Message> message = messageService.findAllByEmail(email);
        model.addAttribute("AlanMessages", message);
        model.addAttribute("tagList", TagName.values());
        return "components/alanTab :: alan_tab";
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

    /*@GetMapping("/get-feed")
    public String getFeed(Model model) {
        // 멤버
        String email = getEmail(); // TODO: 테스트용. 나중에 수정하기!
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

        return "components/feed2";
    }*/

    @GetMapping("get-feed")

    public String getFeed(
            Model model,
            @PageableDefault(size = 4) Pageable pageable
    ) {
        // 멤버
        String email = getEmail();
        Slice<FeedDTO.FeedWithImageResponseDTO> feedSlice;
        if (email != null && !email.equals("anonymousUser")) {
            MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
            feedSlice = feedService.RecommendFeedWithImage(email, pageable);
            model.addAttribute("feedSlice", feedSlice);
            model.addAttribute("feedMember", member);

        } else  {
            model.addAttribute("feedMember", null);
            feedSlice = feedService.RecommendFeedWithImage("", pageable);
            model.addAttribute("feedSlice", feedSlice);
        }
        if (!feedSlice.hasNext() && feedSlice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
        return "components/feed";
    }

    @GetMapping("/get-cafeCard")
    public String getCafeCard(@RequestParam String query, @PageableDefault(size = 6) Pageable pageable ,Model model) {
        Slice<CafeDTO.findAllWithImageAndTagResponse> cafeSlice =  cafeService.searchCafes(query, pageable);
        model.addAttribute("cafeSlice", cafeSlice);
        if (!cafeSlice.hasNext() && cafeSlice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
        return "components/cafeCard";
    }

    @GetMapping("/get-search-feed")
    public String getSearchFeed(@RequestParam String query, @PageableDefault(size = 5) Pageable pageable, Model model) {
        String email = getEmail();
        Slice<FeedDTO.FeedWithImageResponseDTO> feedSlice = feedService.searchFeed(email, query, pageable);
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("feedMember", member);
        model.addAttribute("feedSlice", feedSlice);
        if (!feedSlice.hasNext() && feedSlice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
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
        if (!slice.hasNext() && slice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
        model.addAttribute("feedSlice", slice.getContent());
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
        if (!slice.hasNext() && slice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
        model.addAttribute("feedSlice", slice);
        return "components/feed";
    }

    @GetMapping("/get-search-member")
    public String getSearchMember(@RequestParam String query, @PageableDefault Pageable pageable, Model model) {
        String email = getEmail();
        String memberId = memberService.findMemberByEmail(email).getMemberId();
        Slice<MemberDTO.SearchMemberDTO> memberSlice = memberService.searchMember(memberId, query, pageable);
        model.addAttribute("followMembers", memberSlice.getContent());
        model.addAttribute("hasNext", memberSlice.hasNext());
        if (!memberSlice.hasNext() && memberSlice.getNumberOfElements() == 0) {
            throw new NoMoreContentException("존재하지 않음");
        }
        return "components/followerList :: followList(title='search')";
    }

    @GetMapping("/get-main-card")
    public String getMainCafeCard(Model model) {
        List<CafeDTO.findAllWithImageAndTagResponse> cafeSlice =  cafeService.weekTop3Cafe();
        model.addAttribute("cafeSlice", cafeSlice);
        return "components/cafeCard :: cafeCards";
    }

    @GetMapping("/get-feed-comment/{feedId}")
    public String getFeedComment(@PathVariable("feedId")String feedId, Model model) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("feedMember", member);
        List<FeedDTO.FeedWithImageResponseDTO> list = feedService.findFeedForComment(email, feedId);
        model.addAttribute("feedOne", list);
        return "components/comment :: comment_update";
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
