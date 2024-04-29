package com.grinder.domain.enums;

public enum Role {
    USER("일반회원"), VERIFIED_USER("인증회원"), SELLER("판매자"), ADMIN("관리자");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
