package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blacklist", uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "blocked_member_id"})})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_id", updatable = false)
    private Long blacklistId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "blocked_member_id", nullable = false)
    private Member blockedMember;
}
