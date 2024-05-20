package com.grinder.repository.queries;

import com.grinder.config.TestConfig;
import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.TagName;
import com.grinder.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class BookmarkQueryRepositoryTest {
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
    private BlacklistRepository blacklistRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BookmarkQueryRepository bookmarkQueryRepository;

    @BeforeEach
    public void setUp() {
        Member member = memberRepository.save(Member.builder().email("test@test.com").nickname("test").phoneNum("01012341234").password("1234").build());
        Member member1 = memberRepository.save(Member.builder().email("test1@test.com").nickname("test1").phoneNum("01012341234").password("1234").build());
        Member member2 = memberRepository.save(Member.builder().email("test2@test.com").nickname("test2").phoneNum("01012341234").password("1234").build());
        Blacklist blacklist = blacklistRepository.save(Blacklist.builder().member(member).blockedMember(member1).build());
        Blacklist blacklist1 = blacklistRepository.save(Blacklist.builder().member(member).blockedMember(member2).build());
        Image image = imageRepository.save(Image.builder().contentType(ContentType.MEMBER).contentId(member.getMemberId()).imageUrl("1234").build());
        Image image1 = imageRepository.save(Image.builder().contentType(ContentType.MEMBER).contentId(member1.getMemberId()).imageUrl("1234").build());
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
    @DisplayName("북마크 목록 가져오기")
    void findAllBookmarkSlice() {
        Slice<BookmarkDTO.findAllResponse> find = bookmarkQueryRepository.findAllBookmarkSlice("test@test.com", Pageable.ofSize(1));

        Assertions.assertThat(find).extracting("cafeName").contains("그라인더0");
        Assertions.assertThat(find).extracting("cafeName").doesNotContain("그라인더1");
    }

    @Test
    @DisplayName("이미 북마크가 되어있는지 확인")
    void existsByMemberEmailAndCafeId() {
        List<Cafe> cafeList = cafeRepository.findAll();
        Cafe cafe = null;
        for (Cafe c : cafeList) {
            if (c.getName().equals("그라인더0")) cafe = c;
        }
        boolean find = bookmarkQueryRepository.existsByMemberEmailAndCafeId("test@test.com", cafe.getCafeId());

        Assertions.assertThat(find).isTrue();
    }
}