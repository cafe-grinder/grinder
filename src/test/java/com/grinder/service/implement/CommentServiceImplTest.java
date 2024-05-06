package com.grinder.service.implement;

import com.grinder.domain.entity.*;
import com.grinder.domain.enums.Role;
import com.grinder.repository.CafeRepository;
import com.grinder.repository.CommentRepository;
import com.grinder.repository.FeedRepository;
import com.grinder.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CommentServiceImplTest {
    @Autowired
    public FeedRepository feedRepository;
    @Autowired
    public CafeRepository cafeRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public CommentRepository commentRepository;

    private Member member1;
    private Member member2;
    private Cafe cafe1;
    private Cafe cafe2;
    private Feed feed1;
    private Feed feed2;
    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private Comment comment4;
    private Comment comment5;
    private Comment comment6;
    private Comment comment7;
    private Comment comment8;

    @BeforeEach
    public void makeExample() {
        member1 = Member.builder()
                .email("member1@example.com")
                .nickname("user1")
                .password("password1")
                .role(Role.MEMBER)
                .phoneNum("1234567890")
                .build();

        member2 = Member.builder()
                .email("member2@example.com")
                .nickname("user2")
                .password("password2")
                .role(Role.MEMBER)
                .phoneNum("9876543210")
                .build();

        cafe1 = Cafe.builder()
                .name("Cafe A")
                .address("123 Main St, City A")
                .phoneNum("1112223333")
                .averageGrade(4)
                .build();

        cafe2 = Cafe.builder()
                .name("Cafe B")
                .address("456 Elm St, City B")
                .phoneNum("4445556666")
                .averageGrade(3)
                .build();

        feed1 = Feed.builder()
                .member(member1)
                .cafe(cafe1)
                .content("Great coffee and atmosphere!")
                .hits(100)
                .isVisible(true)
                .grade(5)
                .build();

        feed2 = Feed.builder()
                .member(member1)
                .cafe(cafe2)
                .content("Nice place to hang out with friends.")
                .hits(50)
                .isVisible(true)
                .grade(4)
                .build();

        comment1 = Comment.builder()
                .feed(feed1)
                .member(member1)
                .content("Great post!")
                .isVisible(true)
                .build();

        comment2 = Comment.builder()
                .feed(feed1)
                .member(member2)
                .content("I agree!")
                .isVisible(true)
                .build();

        comment3 = Comment.builder()
                .parentComment(comment2)
                .feed(feed1)
                .member(member1)
                .content("Thank you!")
                .isVisible(true)
                .build();

        comment4 = Comment.builder()
                .feed(feed1)
                .member(member1)
                .content("Nice photo!")
                .isVisible(false)
                .build();

        comment5 = Comment.builder()
                .feed(feed2)
                .member(member2)
                .content("Beautiful!")
                .isVisible(true)
                .build();

        comment6 = Comment.builder()
                .parentComment(comment5)
                .feed(feed2)
                .member(member2)
                .content("Great angle!")
                .isVisible(false)
                .build();

        comment7 = Comment.builder()
                .parentComment(comment5)
                .feed(feed2)
                .member(member1)
                .content("Interesting topic!")
                .isVisible(false)
                .build();

        comment8 = Comment.builder()
                .parentComment(comment5)
                .feed(feed2)
                .member(member2)
                .content("I'd like to learn more.")
                .isVisible(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        cafeRepository.save(cafe1);
        cafeRepository.save(cafe2);
        feedRepository.save(feed1);
        feedRepository.save(feed2);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        commentRepository.save(comment4);
        commentRepository.save(comment5);
        commentRepository.save(comment6);
        commentRepository.save(comment7);
        commentRepository.save(comment8);
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    void findComment() {
        String comment1Id = comment1.getCommentId();
        String comment2Id = comment2.getCommentId();
        String comment3Id = comment3.getCommentId();
        String comment4Id = comment4.getCommentId();

        Comment findComment1 = commentRepository.findById(comment1Id).orElseThrow(() -> new IllegalArgumentException("comment id(" + comment1Id + ")를 찾을 수 없습니다."));
        Comment findComment2 = commentRepository.findById(comment2Id).orElseThrow(() -> new IllegalArgumentException("comment id(" + comment2Id + ")를 찾을 수 없습니다."));
        Comment findComment3 = commentRepository.findById(comment3Id).orElseThrow(() -> new IllegalArgumentException("comment id(" + comment3Id + ")를 찾을 수 없습니다."));
        Comment findComment4 = commentRepository.findById(comment4Id).orElseThrow(() -> new IllegalArgumentException("comment id(" + comment4Id + ")를 찾을 수 없습니다."));

        assertEquals(comment1, findComment1);
        assertEquals(comment2, findComment2);
        assertEquals(comment3, findComment3);
        assertEquals(comment4, findComment4);
    }

    @Test
    @DisplayName("부모 댓글 리스트 조회 테스트")
    void findParentCommentList() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        // comment3은 자식댓글, comment4는 isVisible==false

        List<Comment> findCommentList = commentRepository.findByFeed_FeedIdAndParentCommentIsNullAndIsVisibleTrue(feed1.getFeedId());

        assertEquals(commentList, findCommentList);
    }

    @Test
    @DisplayName("댓글의 자식 댓글 리스트 조회 테스트")
    void findChildrenCommentList() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment8);
        // comment6, comment7은 isVisible==false

        List<Comment> findCommentList = commentRepository.findByParentComment_CommentIdAndIsVisibleTrue(comment5.getCommentId());

        assertEquals(commentList, findCommentList);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {
        String comment1Id = comment1.getCommentId();
        String updateContent = "update content!";
        comment1.updateContent(updateContent);
        commentRepository.save(comment1);

        Comment findComment1 = commentRepository.findById(comment1Id).orElseThrow(() -> new IllegalArgumentException("comment id(" + comment1Id + ")를 찾을 수 없습니다."));

        assertEquals(updateContent, findComment1.getContent());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() {
    }
}