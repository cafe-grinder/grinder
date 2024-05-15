package com.grinder.controller.view;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Image;
import com.grinder.domain.entity.Tag;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.TagName;
import com.grinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewFeedController {
    private final FeedService feedService;
    private final ImageService imageService;
    private final TagService tagService;

    @GetMapping("/feed/newfeed")
    public String newFeed(
            Model model,
            @RequestParam(required = false) String feedId     // id가 없으면 null
    ) {
        if (feedId == null) {  // 피드 등록
            model.addAttribute("newfeed", new FeedDTO.NewFeedResponseDTO());
        } else {  // 피드 수정
            Feed feed = feedService.findFeed(feedId);
            List<Image> imageList = imageService.findAllImage(feedId, ContentType.FEED);
            List<Tag> tagList = tagService.findAllTag(feedId);
            FeedDTO.NewFeedResponseDTO newFeed = new FeedDTO.NewFeedResponseDTO(feed, imageList, tagList);
            model.addAttribute("newfeed", newFeed);
        }

        model.addAttribute("tagList", TagName.values());

        return "feedWriteForm";
    }
}
