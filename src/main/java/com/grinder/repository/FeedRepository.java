package com.grinder.repository;

import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    List<Feed> findAllByIsVisibleTrue();
    List<Feed> findFeedsByCafe_CafeId(String cafeId);

    @Query("SELECT f FROM Feed f")
    List<Feed> findFeedsForRankUpdate(long limit, long offset);

    @Modifying
    @Transactional
    @Query("UPDATE Feed f SET f.rank = :rank WHERE f.feedId = :feedId")
    void updateFeedRank(@Param("feedId") String feedId, @Param("rank") Integer rank);
}
