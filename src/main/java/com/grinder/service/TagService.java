package com.grinder.service;

import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Tag;
import com.grinder.domain.enums.TagName;
import com.grinder.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag findTag(String tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new IllegalArgumentException("tag id(" + tagId + ")를 찾울 수 없습니다."));
    }

    public List<Tag> findAllTag(String feedId) {
        return tagRepository.findByFeed_FeedId(feedId);
    }

    public void saveTag(Feed feed, List<String> tagNameList) {
        for (String tagName : tagNameList) {
            tagRepository.save(
                    Tag.builder()
                            .feed(feed)
                            .tagName(TagName.valueOf(tagName))
                            .build()
            );
        }
    }

    public void deleteTag(String feedId) {
        List<Tag> tagList = findAllTag(feedId);
        tagRepository.deleteAll(tagList);
    }
}
