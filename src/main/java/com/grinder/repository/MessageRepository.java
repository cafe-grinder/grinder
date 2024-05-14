package com.grinder.repository;

import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    Optional<Message> findByReceiveMember(Member member);
    List<Message> findAllByReceiveMember_Email(String email);
}
