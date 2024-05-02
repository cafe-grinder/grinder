package com.grinder.service;

import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Report;
import com.grinder.repository.ReportRepository;
import com.grinder.service.implement.ReportServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static com.grinder.domain.dto.ReportDTO.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @InjectMocks
    ReportServiceImpl reportService;

    @Mock
    ReportRepository reportRepository;

    @DisplayName("신고 내역 조회")
    @Test
    void testFindAllReports() {
        //given
        List<Report> reportList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            reportList.add(Report.builder().member(new Member()).build());
        }

        doReturn(reportList).when(reportRepository).findAll();

        //when
        List<FindReportDTO> reportDTOList = reportService.findAllReports();

        //then
        assertThat(reportDTOList.size()).isEqualTo(2);
    }

}