package com.grinder.repository.queries;

import com.grinder.config.TestConfig;
import com.grinder.domain.dto.CommentDTO;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class CommentQueryRepositoryTest {
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
    private CommentRepository commentRepository;
    @Autowired
    private CommentQueryRepository commentQueryRepository;

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
//        Feed feed1 = feedRepository.save(Feed.builder().cafe(cafe).member(member1).content("내용1").grade(4).build());
        Comment Pcomment = commentRepository.save(Comment.builder().member(member).feed(feed).content("댓글 내용1").build());
//        Comment Pcomment1 = commentRepository.save(Comment.builder().member(member).feed(feed).content("댓글 내용2").build());
        Comment Ccomment = commentRepository.save(Comment.builder().member(member).feed(feed).content("대댓글 내용1").parentComment(Pcomment).build());
        Tag tag = tagRepository.save(Tag.builder().tagName(TagName.ACCESSIBLE).feed(feed).build());
        Tag tag1 = tagRepository.save(Tag.builder().tagName(TagName.CLEAN).feed(feed).build());
        Tag tag2 = tagRepository.save(Tag.builder().tagName(TagName.FAST_WIFI).feed(feed).build());
        Heart heart = heartRepository.save(Heart.builder().member(member).contentType(ContentType.FEED).contentId(feed.getFeedId()).build());
    }

    @Test
    @DisplayName("댓글 찾기")
    void findComment() {
        Feed feed = feedRepository.findAll().get(0);
        Comment comment = commentRepository.findByFeed_FeedIdAndParentCommentIsNullAndIsVisibleTrue(feed.getFeedId()).get(0);
        CommentDTO.FindCommentDTO find = commentQueryRepository.findComment(comment.getCommentId()).orElse(null);

        Assertions.assertThat(find).extracting("content").isEqualTo("댓글 내용1");
    }
}