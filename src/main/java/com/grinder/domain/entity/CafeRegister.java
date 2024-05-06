package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "cafe_register")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeRegister {

    @Id
    @Column(name = "register_id", updatable = false, length = 36)
    private String registerId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "phone_num", nullable = false, length = 11)
    private String phoneNum;

    @PrePersist
    public void prePersist() {
        registerId = registerId == null ? UUID.randomUUID().toString() : registerId;
    }
}
