package com.grinder.service.implement;

import com.grinder.domain.entity.Report;
import com.grinder.repository.ReportRepository;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
    @Transactional
    public void deleteReport(String reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 신고글입니다."));
        reportRepository.delete(report);
    }
}
