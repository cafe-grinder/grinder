package com.grinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MEMBER("일반회원"),
    VERIFIED_MEMBER("인증회원"),
    SELLER("판매자"),
    ADMIN("관리자");

    private final String value;
}
