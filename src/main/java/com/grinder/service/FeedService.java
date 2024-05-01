package com.grinder.service;

import com.grinder.domain.dto.FeedRequestDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.grinder.repository.CafeRepository;
import com.grinder.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final ImageService imageService;
    private final TagService tagService;
    private final CafeRepository cafeRepository;

    public Feed findFeed(String feedId) {
        return feedRepository.findById(feedId).orElseThrow(() -> new IllegalArgumentException("feed id(" + feedId + ")를 찾울 수 없습니다."));
    }

    public Feed saveFeed(String feedId, FeedRequestDTO request, Member member){
        // Feed 저장
        Cafe cafe = cafeRepository.findById(request.getCafeId()).orElseThrow(() -> new IllegalArgumentException("cafe id를 찾울 수 없습니다.")); // todo: CafeService로 수정
        Feed feed = new Feed(feedId, request, member, cafe);

        // Tag 저장
        tagService.saveTag(feed, request.getTagNameList());

        // Image 저장
        imageService.saveFeedImage(feedId, ContentType.FEED, request.getImageUrlList());

        return feedRepository.save(feed);
    }

    public List<Feed> findAllFeed() {   // isVisible == true 인 피드만 조회
        return feedRepository.findAllByIsVisibleTrue();
    }

    public void updateFeed(String feedId, FeedRequestDTO request) {
        // 피드 수정
        Feed feed = findFeed(feedId);
        Cafe cafe = cafeRepository.findById(request.getCafeId()).orElseThrow(() -> new IllegalArgumentException("cafe id를 찾울 수 없습니다.")); // todo: CafeService로 수정
        feed.updateFeed(cafe, request.getContent(), request.getGrade());

        // 태그 수정
        tagService.deleteTag(feedId);
        tagService.saveTag(feed, request.getTagNameList());

        // 이미지 수정
        imageService.deleteFeedImage(feedId, ContentType.FEED);
        imageService.saveFeedImage(feedId, ContentType.FEED, request.getImageUrlList());
    }

    public void deleteFeed(String feedId) {
        Feed feed = findFeed(feedId);
        feed.notVisible();
        feedRepository.save(feed);
    }
}
