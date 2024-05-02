package com.grinder.service;

import java.util.List;
import static com.grinder.domain.dto.ReportDTO.*;

public interface ReportService {

    List<FindReportDTO> findAllReports();

    void deleteReport(String reportId);

}
