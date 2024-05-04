package com.grinder.service.implement;

import com.grinder.domain.entity.Report;
import com.grinder.exception.ContentTypeException;
import com.grinder.repository.ReportRepository;
import com.grinder.service.CommentService;
import com.grinder.service.FeedService;
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
    private final CommentService commentService;
    private final FeedService feedService;

    @Override
    public List<FindReportDTO> findAllReports() {
        List<Report> reportList = reportRepository.findAll();

        List<FindReportDTO> reportDTOList = reportList.stream().map(report -> new FindReportDTO(report)).toList();

        return reportDTOList;
    }

    @Override
    public Report findReportById(String reportId) throws NoSuchElementException {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 신고글입니다."));
        return report;
    }

    @Override
    @Transactional
    public void deleteReport(String reportId) {
        Report report = findReportById(reportId);
        reportRepository.delete(report);
    }

    @Override
    @Transactional
    public void deleteAllReportByContentId(Report report) {
        List<Report> reportList = reportRepository.findByContentId(report.getContentId());
        reportRepository.deleteAll(reportList);
    }

    @Override
    @Transactional
    public void deleteContent(String reportId) {
        Report report = findReportById(reportId);
        switch (report.getContentType()) {
            case COMMENT :
                commentService.deleteComment(report.getContentId());
                break;

            case FEED :
                feedService.deleteFeed(report.getContentId());
                break;

            default :
                throw new ContentTypeException("신고된 컨텐츠의 타입이 잘못되었습니다.");
        }

        deleteAllReportByContentId(report);
    }
}
