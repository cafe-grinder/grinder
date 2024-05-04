package com.grinder.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.grinder.domain.dto.ReportDTO.*;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Report;
import com.grinder.service.implement.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ReportController.class)
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class ReportControllerTest {

    @MockBean
    ReportServiceImpl reportService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser()
    void testFindAllReports() throws Exception {
        List<FindReportDTO> reportDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            reportDTOList.add(new FindReportDTO(Report.builder().member(new Member()).build()));
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedResponse =  objectMapper.writeValueAsString(reportDTOList);

        doReturn(reportDTOList).when(reportService).findAllReports();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mockMvc.perform(get("/api/report/find"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
