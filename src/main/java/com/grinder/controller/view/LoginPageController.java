package com.grinder.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class LoginPageController {
    //회원가입 로그인 페이지 통일해야되면 이름 수정하기
    @GetMapping("/page/login")
    public String viewMyPage() {
        return "loginForm";
    }
}
