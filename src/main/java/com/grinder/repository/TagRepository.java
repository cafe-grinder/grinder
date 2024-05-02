package com.grinder.repository;

import com.grinder.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByFeed_FeedId(String feedId);
}
