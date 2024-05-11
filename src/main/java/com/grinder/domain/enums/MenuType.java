package com.grinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuType {
    COFFEE("커피"),
    BEVERAGE("음료"),
    DESSERT("디저트");

    private final String value;

    public static MenuType fromString(String text) {
        for (MenuType type : MenuType.values()) {
            if (type.getValue().equals(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("해당 타입 " + text + "이 존재하지 않습니다");
    }
}
