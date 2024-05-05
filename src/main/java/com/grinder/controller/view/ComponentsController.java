package com.grinder.controller.view;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ComponentsController {
    private final MemberService memberService;

    @GetMapping("/get-header")
    public String getHeader(Authentication authentication, Model model) {
        MemberDTO.FindMemberDTO member = null;
        try {
            String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
            member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
        } catch (Exception e) {}
        model.addAttribute("member",member);
        return "components/header :: headers";
    }
}
