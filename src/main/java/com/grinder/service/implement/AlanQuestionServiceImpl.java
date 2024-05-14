package com.grinder.service.implement;

import com.grinder.domain.dto.AlanDTO;
import com.grinder.domain.entity.AnalysisTag;
import com.grinder.domain.entity.Message;
import com.grinder.repository.MessageRepository;
import com.grinder.service.AlanQuestionService;
import com.grinder.service.AnalysisTagService;
import com.grinder.utils.AlanAPI;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlanQuestionServiceImpl implements AlanQuestionService {

    private final AlanAPI alanAPI;
    private final AnalysisTagService analysisTagService;
    private final MessageRepository messageRepository;

    @Override
    public AlanDTO.AlanResponse anyQuestion(String question) {
        return alanAPI.anyQuestion(question);
    }

    @Transactional
    @Override
    public boolean recommendCafe(String email) {
        //email을 사용하여 AnalysisTag 데이터 가져와서 alan에 전송
        try {
            AnalysisTag analysisTag = analysisTagService.findByEmail(email);
            AlanDTO.AlanResponse response = alanAPI.recommendCafeByTag(analysisTag.getTagList());
            //앨런에게 받은 데이터를 message에 저장
            messageRepository.save(Message.builder().adminName("Alan")
                    .receiveMember(analysisTag.getMember())
                    .content(response.getContent()).build());
        } catch (EntityNotFoundException e) {
            log.info("(" + email + ")" + e);
            return false;
        }
        return true;
    }

}
