package com.grinder.controller.view;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;

    @GetMapping
    public String index(Model model) {
        String email = getEmail();
        if (email != null && !email.equals("anonymousUser")) {
            MemberDTO.FindMemberDTO member = new MemberDTO.FindMemberDTO(memberService.findMemberByEmail(email));
            model.addAttribute("member", member);
        } else  {
            model.addAttribute("member", null);
        }
        return "index";
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
