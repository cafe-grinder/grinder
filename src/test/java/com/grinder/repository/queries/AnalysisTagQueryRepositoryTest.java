package com.grinder.repository.queries;

import com.grinder.config.TestConfig;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.TagName;
import com.grinder.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class AnalysisTagQueryRepositoryTest {
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private CafeRepository cafeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnalysisTagQueryRepository analysisTagQueryRepository;

    @BeforeEach
    public void setUp() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test").phoneNum("01012341234").password("1234").build());
        Member member1 = memberRepository.save(Member.builder().email("test1@test.com").nickname("test1").phoneNum("01012341234").password("1234").build());
        Cafe cafe = cafeRepository.save(Cafe.builder().name("그라인더0").phoneNum("01012341234").address("서울시 강남구").build());
        Cafe cafe1 = cafeRepository.save(Cafe.builder().name("그라인더1").phoneNum("01012341234").address("서울시 강남구").build());
        Bookmark bookmark = bookmarkRepository.save(Bookmark.builder().cafe(cafe).member(member).build());
        Feed feed = feedRepository.save(Feed.builder().cafe(cafe).member(member).content("내용").grade(5).build());
        Feed feed1 = feedRepository.save(Feed.builder().cafe(cafe).member(member1).content("내용1").grade(4).build());
        Tag tag = tagRepository.save(Tag.builder().tagName(TagName.ACCESSIBLE).feed(feed).build());
        Tag tag1 = tagRepository.save(Tag.builder().tagName(TagName.CLEAN).feed(feed).build());
        Tag tag2 = tagRepository.save(Tag.builder().tagName(TagName.FAST_WIFI).feed(feed).build());
        Heart heart = heartRepository.save(Heart.builder().member(member).contentType(ContentType.FEED).contentId(feed.getFeedId()).build());
    }

    @Test
    @DisplayName("회원 태그 분석")
    void analysisMemberTag() {
        List<String> find = analysisTagQueryRepository.AnalysisMemberTag("test@test.com");
        Assertions.assertEquals(find.size(), 3);
    }
}