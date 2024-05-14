package com.grinder.repository;

import com.grinder.domain.entity.AnalysisTag;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisTagRepository extends JpaRepository<AnalysisTag, Long> {
    Optional<AnalysisTag> findByMember_MemberIdAndUpdatedAtBefore(String memberId, LocalDateTime update);
    Optional<AnalysisTag> findByMember_Email(String email);
}
