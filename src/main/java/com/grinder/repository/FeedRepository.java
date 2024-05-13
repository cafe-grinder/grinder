package com.grinder.repository;

import com.grinder.domain.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    List<Feed> findAllByIsVisibleTrue();
    List<Feed> findFeedsByCafe_CafeId(String cafeId);
}
