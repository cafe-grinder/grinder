package com.grinder.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class AccountPageController {
    //회원가입 로그인 페이지 통일해야되면 이름 수정하기 -> 나중에!
    @GetMapping("/page/login")
    public String viewLoginPage() {
        return "loginForm";
    }

    @GetMapping("/page/signup")
    public String viewSignupPage(@RequestParam(name = "email", required = false) String email){
        return "addMemberForm";
    }

    @GetMapping("/page/find/account")
    public String viewFindAccountPage(){return "findAccountForm";}

    @GetMapping("/page/welcome")
    public String viewWelcomePage(){return "welcomeForm";}

    @GetMapping("/page/find/account/finish")
    public String viewFindAccountFinishPage(){return "findAccountFinishForm";}
}
