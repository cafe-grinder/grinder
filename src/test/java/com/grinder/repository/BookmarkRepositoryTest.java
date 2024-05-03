package com.grinder.repository;

import com.grinder.domain.entity.Blacklist;
import com.grinder.domain.entity.Bookmark;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MemberRepository memberRepository;
    @DisplayName("북마크 추가")
    @Test
    void testSaveBookmark() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Cafe cafe = cafeRepository.save(Cafe.builder().name("그라인더").address("사랑시 고백구 행복동 794-2").phoneNum("01012345678").build());
        Bookmark saved = bookmarkRepository.save(Bookmark.builder().member(member).cafe(cafe).build());

        Bookmark found = bookmarkRepository.findById(1L).orElseThrow();

        Assertions.assertEquals(saved.getCafe().getCafeId(), found.getCafe().getCafeId());
    }
}