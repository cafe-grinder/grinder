package com.grinder.service.implement;

import com.grinder.domain.dto.AlanDTO;
import com.grinder.service.AlanQuestionService;
import com.grinder.utils.AlanAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlanQuestionServiceImpl implements AlanQuestionService {

    private final AlanAPI alanAPI;

    public AlanDTO.AlanResponse anyQuestion(String question) {
        return alanAPI.anyQuestion(question);
    }
}
