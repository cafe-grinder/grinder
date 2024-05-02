package com.grinder.controller;

import com.grinder.domain.dto.ReportDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/find")
    public void findAllReports(Model model) {
        List<ReportDTO.FindReportDTO> reportList = reportService.findAllReports();
        model.addAttribute("reportList", reportList);
    }

    @DeleteMapping("/delete/{reportId}")
    @ResponseBody
    public ResponseEntity<SuccessResult> deleteReport(@PathVariable String reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }
}
