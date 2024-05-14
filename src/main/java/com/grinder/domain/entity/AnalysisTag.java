package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "analysis_tag", indexes = {
        @Index(name = "idx_memberId", columnList = "member_id")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analysis_id", updatable = false)
    private Long analysisId;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "tag_list", nullable = false)
    private String tagList;

    // 태그 추가 메소드
    public void addTags(String newTags) {
        StringBuilder sb = new StringBuilder();

        if (this.tagList != null && !this.tagList.isEmpty()) sb.append(this.tagList).append("/");
        sb.append(newTags);

        this.tagList = sb.toString();

        // 태그 길이 확인 및 앞부분 제거
        if (this.tagList.length() >= 1800) {
            this.tagList = removeLeadingTags(this.tagList, 7);
        }
    }

    // 앞부분의 태그 7개를 제거하는 메소드
    private String removeLeadingTags(String tags, int numTagsToRemove) {
        String[] tagArray = tags.split("/");
        if (tagArray.length <= numTagsToRemove) {
            return "";  // 모든 태그를 제거
        }

        List<String> tagList = new ArrayList<>(Arrays.asList(tagArray));
        for (int i = 0; i < numTagsToRemove; i++) {
            tagList.remove(0);
        }

        return String.join("/", tagList);
    }
}
