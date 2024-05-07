package com.grinder.controller.view;

import com.grinder.domain.dto.BlacklistDTO;
import com.grinder.domain.dto.FollowDTO;
import com.grinder.domain.dto.MemberDTO;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.BlacklistService;
import com.grinder.service.FollowService;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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


    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
