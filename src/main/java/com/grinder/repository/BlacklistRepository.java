package com.grinder.repository;

import com.grinder.domain.entity.Blacklist;
import com.grinder.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Optional<List<Blacklist>> findAllByMember(Member member);
    Long countAllByMember(Member member);
}
