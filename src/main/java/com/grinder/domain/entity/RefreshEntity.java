package com.grinder.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshEntity {

    @Id
    @Column(name = "refresh_id", updatable = false, length = 36)
    private String refreshId;

    private String refresh;
    private String email;
    private String expiration;


    @PrePersist
    public void prePersist() {
        refreshId = refreshId == null ? UUID.randomUUID().toString() : refreshId;
    }

}
