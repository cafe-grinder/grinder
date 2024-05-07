package com.grinder.service;

import com.grinder.domain.entity.Report;

import java.util.List;
import static com.grinder.domain.dto.ReportDTO.*;

public interface ReportService {

    List<FindReportDTO> findAllReports();

    Report findReportById(String reportId);

    void deleteReport(String reportId);

    void deleteContent(String reportId);

    void deleteAllReportByContentId(Report report);
}
