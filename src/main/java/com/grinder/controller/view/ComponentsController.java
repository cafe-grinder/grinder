package com.grinder.controller.view;

import com.grinder.domain.dto.*;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/get-header")
    public String getHeader(Model model) {
        String email = getEmail();
        MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        model.addAttribute("headerMember",member);
        return "components/header :: headers";
    }

    @GetMapping("/get-follower")
    public String getFollower(Model model, @PageableDefault Pageable pageable) {
        String email = getEmail();
        List<FollowDTO.findAllFollowerResponse> list = new ArrayList<>();
        if (email != null) {
            if (!email.equals("anonymousUser"))
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


    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
