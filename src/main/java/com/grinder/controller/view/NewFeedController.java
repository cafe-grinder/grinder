package com.grinder.controller.view;

import ch.qos.logback.core.model.Model;
import com.grinder.service.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class NewFeedController {
    private final MemberService memberService;
    private final CafeService cafeService;
    private final FeedService feedService;
    private final CommentService commentService;
    private final TagService tagService;

    @GetMapping("/feed/newfeed")
    public String newFeed(Model model) {


        return "feedWriteForm";
    }
}
