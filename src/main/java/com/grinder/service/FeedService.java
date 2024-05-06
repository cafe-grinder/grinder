package com.grinder.service;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.Feed;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {
    // feedId로 피드 찾기
    Feed findFeed(String feedId);
    // 피드 생성
    Feed saveFeed(FeedDTO.FeedRequestDTO request, String memberEmail, MultipartFile file);
    // 피드 조회 (비회원)
    List<Feed> findAllFeed();
    // 피드 수정
    Feed updateFeed(String feedId, FeedDTO.FeedRequestDTO request);
    // 피드 삭제(isVisible == false)
    void deleteFeed(String feedId);
}
