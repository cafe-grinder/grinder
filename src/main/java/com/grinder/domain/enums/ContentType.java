package com.grinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContentType {
    MEMBER("회원"),
    FEED("피드"),
    CAFE("카페"),
    MENU("메뉴"),
    COMMENT("댓글");

    private final String value;

    // 문자열 값으로부터 ContentType을 반환하는 정적 메소드
    public static ContentType fromString(String text) {
        for (ContentType type : ContentType.values()) {
            if (type.getValue().equals(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
