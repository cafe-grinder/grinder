package com.grinder.controller.entity;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CommentService;
import com.grinder.service.FeedService;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.grinder.domain.dto.ReportDTO.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/find")
    public ResponseEntity<List<FindReportDTO>> findAllReports() {
        List<FindReportDTO> reportList = reportService.findAllReports();

        return ResponseEntity.ok(reportList);
    }

    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<SuccessResult> deleteReport(@PathVariable String reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(new SuccessResult("Success", "신고 요청이 삭제되었습니다."));
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<SuccessResult> deleteContent(@PathVariable String reportId) {
        reportService.deleteContent(reportId);

        return ResponseEntity.ok(new SuccessResult("report_accepted", "신고된 컨텐츠가 삭제되었습니다."));
    }

}
