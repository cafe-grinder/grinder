package com.grinder.service;

import com.grinder.domain.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    boolean existNonCheckMessage(String email);
    List<Message> findAllByEmail(String email);
}
