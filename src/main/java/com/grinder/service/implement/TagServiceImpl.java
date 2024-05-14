package com.grinder.service.implement;

import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Tag;
import com.grinder.domain.enums.TagName;
import com.grinder.repository.TagRepository;
import com.grinder.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public Tag findTag(String tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public List<Tag> findAllTag(String feedId) {
        return tagRepository.findByFeed_FeedId(feedId);
    }

    public void saveTag(Feed feed, List<String> tagNameList) {
        if (tagNameList == null || tagNameList.isEmpty()) {
            tagNameList = new ArrayList<>();
        }
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
