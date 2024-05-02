package com.grinder.service.implement;

import com.grinder.domain.dto.ReportDTO;
import com.grinder.domain.entity.Report;
import com.grinder.repository.ReportRepository;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<ReportDTO.FindReportDTO> findAllReports() {
        List<Report> reportList = reportRepository.findAll();

        List<ReportDTO.FindReportDTO> reportDTOList = reportList.stream().map(report -> new ReportDTO.FindReportDTO(report)).toList();

        return reportDTOList;
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }
}
