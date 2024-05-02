package com.grinder.repository;

import com.grinder.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByFeed_FeedId(String feedId);
}
