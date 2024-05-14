package com.grinder.service;

import com.grinder.domain.dto.AlanDTO;

public interface AlanQuestionService {
    AlanDTO.AlanResponse anyQuestion(String question);

    public boolean recommendCafe(String email);
}
