package com.grinder.controller.view;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import com.grinder.service.FollowService;
import com.grinder.service.ImageService;
import com.grinder.service.MemberService;
import com.grinder.service.SellerInfoService;
import com.grinder.service.implement.CafeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MemberService memberService;
    private final CafeServiceImpl cafeService;
    private final SellerInfoService sellerInfoService;
    private final ImageService imageService;
    private final FollowService followService;

    @GetMapping("/mypage/{member_id}")
    public String viewMyPage(@PathVariable("member_id")String memberId, Model model) {
        MemberDTO.FindMemberAndImageDTO member = memberService.findMemberAndImageById(memberId);
        String email = getEmail();
        model.addAttribute("isFollow", followService.existFollow(email, member.getEmail()));
        model.addAttribute("myPageMember", member);
        model.addAttribute("connectEmail", email);
        return "myPage";
    }

    @GetMapping("/mycafe/{cafe_id}")
    public String viewMyCafe(@PathVariable("cafe_id")String cafeId, Model model) {
        String email = getEmail();
        Cafe cafe = cafeService.findCafeById(cafeId);
        if (!sellerInfoService.existByMemberAndCafe(cafeId, email)) throw new EntityNotFoundException("관리자가 아닙니다.");
        String imageUrl = imageService.findImageUrlByContentId(cafeId);
        model.addAttribute("myCafe", new CafeDTO.findAllWithImageResponse(cafe, imageUrl));
        return "myCafePage";
    }

    @GetMapping("/myImage")
    public String modifyMyImage() {
        String email = getEmail();
        if (email == null && email.equals("anonymousUser")) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return "myImageUpdate";
    }

    @GetMapping("/myCafeImage/{cafe_id}")
    public String modifyCafeImage(@PathVariable("cafe_id")String cafeId, Model model) {
        String email = getEmail();
        if (email == null && email.equals("anonymousUser")) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        } else {
            if (!sellerInfoService.existByMemberAndCafe(cafeId, email)) {
                throw new IllegalArgumentException("잘못된 접근입니다.");
            }
        }
        model.addAttribute("cafeId", cafeId);
        return "myImageUpdate";
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
