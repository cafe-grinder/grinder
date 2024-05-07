package com.grinder.repository;

import com.grinder.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {

    List<Report> findByContentId(String contentId);
}
