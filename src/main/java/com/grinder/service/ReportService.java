package com.grinder.service;

import com.grinder.domain.entity.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import static com.grinder.domain.dto.ReportDTO.*;

public interface ReportService {

    List<FindReportDTO> findAllReports();

    Report findReportById(String reportId);

    void deleteReport(String reportId);

    void deleteContent(String reportId);

    void deleteAllReportByContentId(Report report);

    Slice<FindReportDTO> searchReportByContentAndType(String keyword, String contentType, Pageable pageable);
}
