package com.grinder.repository;

import com.grinder.domain.entity.SchedulerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerLogRepository extends JpaRepository<SchedulerLog, Long> {
}
