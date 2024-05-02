package com.grinder.repository;

import com.grinder.domain.entity.Bookmark;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByMemberAndCafe(Member member, Cafe cafe);
    Optional<Bookmark> findByMemberAndCafe(Member member, Cafe cafe);
}
