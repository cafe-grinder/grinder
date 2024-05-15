package com.grinder.service;

import com.grinder.domain.entity.AnalysisTag;

import java.util.List;

public interface AnalysisTagService {

    void updateTagList(String email);

    AnalysisTag findByEmail(String email);

    boolean addTagList(List<String> list, AnalysisTag analysisTag);
}
