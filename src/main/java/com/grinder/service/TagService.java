package com.grinder.service;

import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Tag;

import java.util.List;

public interface TagService {
    // tagId로 태그 찾기
    Tag findTag(String tagId);
    // 피드에서 사용한 태그 모두 찾기
    List<Tag> findAllTag(String feedId);
    // 피드에서 사용할 태그 모두 저장
    void saveTag(Feed feed, List<String> tagNameList);
    // 피드에서 사용한 태그 모두 삭제
    void deleteTag(String feedId);
}
