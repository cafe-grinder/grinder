package com.grinder.service;

import com.grinder.domain.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    List<ReportDTO.FindReportDTO> findAllReports();

    void deleteReport(String reportId);

}
