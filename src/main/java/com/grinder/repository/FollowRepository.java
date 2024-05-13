package com.grinder.repository;

import com.grinder.domain.entity.Follow;
import com.grinder.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByMemberAndFollowing(Member follower, Member following);
    boolean existsByMember_EmailAndFollowing_Email(String email, String following);
}
