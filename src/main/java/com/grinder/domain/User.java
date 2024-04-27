package com.grinder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "`user`")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", updatable = false, length = 36)
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "img_id")
    private Image img;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder.Default //@Builder 사용시 필드의 초기값을 적용하기 위해서 사용
    @Enumerated(EnumType.STRING) // enum 값을 db에 문자열로 저장하기 위해서 사용
    @Column(name = "role")
    private Role role = Role.USER;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

}
