package com.grinder.service.implement;

import com.grinder.domain.entity.Report;
import com.grinder.repository.ReportRepository;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.grinder.domain.dto.ReportDTO.*;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<FindReportDTO> findAllReports() {
        List<Report> reportList = reportRepository.findAll();

        List<FindReportDTO> reportDTOList = reportList.stream().map(report -> new FindReportDTO(report)).toList();

        return reportDTOList;
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }
}
