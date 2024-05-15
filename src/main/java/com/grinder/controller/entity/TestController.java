package com.grinder.controller.entity;

import com.grinder.domain.entity.*;
import com.grinder.domain.enums.MenuType;
import com.grinder.repository.*;
import com.grinder.service.MyMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    private final FeedRepository feedRepository;
    private final FollowRepository followRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BlacklistRepository blacklistRepository;
    private final PasswordEncoder passwordEncoder;
    private final MenuRepository menuRepository;
    private final SellerInfoRepository sellerInfoRepository;
    private final MyMenuService myMenuService;

    @GetMapping("/test")
    public void addTestDate() {

        memberRepository.save(Member.builder().email("test@test.com").nickname("test-user-1")
            .password(passwordEncoder.encode("1234"))
            .phoneNum("01012345678").build());
        memberRepository.save(Member.builder().email("test1@test.com").nickname("test-user-2")
            .password(passwordEncoder.encode("1234"))
            .phoneNum("01012345678").build());
        for (int i = 2; i <= 11; i++) {
            memberRepository.save(Member.builder()
                .email("test" + i + "@test.com")
                .nickname("test-user-" + (i + 1))
                .password(passwordEncoder.encode("1234"))
                .phoneNum("01012345678")
                .build());
        }
        for (int i = 1; i <= 11; i++) {
            cafeRepository.save(Cafe.builder()
                .name("그라인더" + i)
                .address("사랑시 고백구 행복동 794-" + i)
                .phoneNum("01012345678")
                .averageGrade((int) (Math.random() * 6))
                .build());
        }

        for (int i = 0; i <= 10; i++) {
            feedRepository.save(Feed.builder()
                .member(memberRepository.findAll().get(i))
                .cafe(cafeRepository.findAll().get(i))
                .content("내용내용입니다~")
                .grade((int) (Math.random() * 6))
                .build());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    followRepository.save(Follow.builder()
                        .following(memberRepository.findAll().get(i))
                        .member(memberRepository.findAll().get(j))
                        .build());
                } catch (Exception e) {
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    bookmarkRepository.save(Bookmark.builder()
                        .member(memberRepository.findAll().get(i))
                        .cafe(cafeRepository.findAll().get(j))
                        .build());
                } catch (Exception e) {
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 5; j < 7; j++) {
                blacklistRepository.save(Blacklist.builder()
                    .member(memberRepository.findAll().get(i))
                    .blockedMember(memberRepository.findAll().get(j))
                    .build());
            }
        }

    }

    @GetMapping("/test1")
    public void addTestDate1() {
        for (int i = 12; i <= 22; i++) {
            cafeRepository.save(Cafe.builder()
                    .name("그라인더" + i)
                    .address("사랑시 고백구 행복동 794-" + i)
                    .phoneNum("01012345678")
                    .averageGrade((int) (Math.random() * 6))
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 12; j < 22; j++) {
                try {
                    bookmarkRepository.save(Bookmark.builder()
                            .member(memberRepository.findAll().get(i))
                            .cafe(cafeRepository.findAll().get(j))
                            .build());
                } catch (Exception e) {
                }
            }
        }
    }

    @GetMapping("/test2")
    public void addTest2() {
        Member member = memberRepository.findByEmail("test@test.com").orElseThrow();
        Cafe cafe = sellerInfoRepository.findAllByMember(member).get(0).getCafe();

        for (int i = 0; i < 10; i++) {
            menuRepository.save(Menu.builder()
                    .allergy("알러지")
                    .menuType(MenuType.BEVERAGE)
                    .cafe(cafe)
                    .name("아메리카노" + i)
                    .details("냠냠저ㅃㅉㅂ" + i)
                    .price("4500")
                    .volume("450ml")
                    .isLimited(false)
                    .build());
        }
    }

    @GetMapping("/test5")
    private void addTest5() {
        Member member = memberRepository.findByEmail("test@test.com").orElse(new Member());
        Cafe cafe = cafeRepository.findAll().get(0);
        sellerInfoRepository.save(SellerInfo.builder().cafe(cafe).member(member).build());
    }
}