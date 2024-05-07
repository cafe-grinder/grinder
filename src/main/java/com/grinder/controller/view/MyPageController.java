package com.grinder.controller.view;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Member;
import com.grinder.domain.enums.ContentType;
import com.grinder.service.FollowService;
import com.grinder.service.ImageService;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/mypage/{member_id}")
    public String viewMyPage(@PathVariable("member_id")String memberId, Model model) {
        MemberDTO.FindMemberAndImageDTO member = memberService.findMemberAndImageById(memberId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = Optional.ofNullable(authentication.getName()).orElse(null);
        email = "test@test.com";
        model.addAttribute("myPageMember", member);
        model.addAttribute("connectEmail", email);
        return "myPage";
    }
}
