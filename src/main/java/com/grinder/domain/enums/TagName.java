package com.grinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TagName {
    CLEAN("🫧매장이 깨끗해요"),
    KIND("☺️직원들이 친절해요"),
    GOOD_INTERIOR("✨인테리어가 좋아요"),
    GOOD_COFFEE("☕커피가 맛있어요"),
    MANY_SEATS("🪑자리가 많아요"),
    GOOD_DESSERT("🍮디저트가 맛있어요"),
    GOOD_VIEW("🌳풍경이 좋아요");

    private final String value;
}


