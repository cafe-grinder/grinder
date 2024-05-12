package com.grinder.controller.view;

import com.grinder.domain.enums.TagName;
import com.grinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
        List<String> tagList = new ArrayList<>();
        for (TagName tagName : TagName.values()) {
            tagList.add(tagName.getValue());
        }
        model.addAttribute("tagList", tagList);

        return "feedWriteForm";
    }
}
