package com.grinder.domain.entity;

import com.grinder.domain.enums.MenuType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "menu")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @Column(name = "menu_id", updatable = false, length = 36)
    private String menuId;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "volume")
    private String volume;

    @Column(name = "allergy", length = 50)
    private String allergy;

    @Column(name = "details", length = 50)
    private String details;

    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type", nullable = false, length = 16)
    private MenuType menuType;

    @Column(name = "is_limited", nullable = false)
    private Boolean isLimited;

    @PrePersist
    public void prePersist() {
        menuId = menuId == null ? UUID.randomUUID().toString() : menuId;
    }
}
