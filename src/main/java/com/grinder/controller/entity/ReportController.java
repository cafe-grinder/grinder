package com.grinder.controller.entity;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CommentService;
import com.grinder.service.FeedService;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
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

}
