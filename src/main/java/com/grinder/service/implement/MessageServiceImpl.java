package com.grinder.service.implement;

import com.grinder.domain.entity.Message;
import com.grinder.repository.MessageRepository;
import com.grinder.repository.queries.MessageQueryRepository;
import com.grinder.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageQueryRepository messageQueryRepository;

    @Override
    public boolean existNonCheckMessage(String email) {
        return messageQueryRepository.existsNonCheck(email);
    }

    @Override
    public List<Message> findAllByEmail(String email) {
        return messageRepository.findAllByReceiveMember_Email(email);
    }
}
