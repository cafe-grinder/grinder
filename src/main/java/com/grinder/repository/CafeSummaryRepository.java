package com.grinder.repository;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.CafeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeSummaryRepository extends JpaRepository<CafeSummary, String> {
}
