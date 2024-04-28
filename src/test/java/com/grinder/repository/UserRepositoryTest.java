package com.grinder.repository;

import com.grinder.domain.Role;
import com.grinder.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("새로운 회원의 데이터를 저장")
    @Test
    void testSaveUser() {
        //given
        LocalDateTime beforeCreate = LocalDateTime.now();

        User user = User.builder().email("test@test.com").nickname("test-user-1").password("1234").build();

        //when
        User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());

        assertThat(savedUser.getCreatedAt()).isBefore(LocalDateTime.now());
        assertThat(savedUser.getCreatedAt()).isAfter(beforeCreate);

        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        assertThat(user.getIsDeleted()).isEqualTo(false);

    }
}