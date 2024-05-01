package com.grinder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDTO {
    // 피드 내용, 이미지, 카페, 평점, 태그
    private String cafeId;
    private String content;
    private List<String> imageUrlList;
    private List<String> tagNameList;
    private Integer grade;
}
