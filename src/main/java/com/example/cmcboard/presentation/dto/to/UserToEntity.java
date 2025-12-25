package com.example.cmcboard.presentation.dto.to;

import com.example.cmcboard.domain.Entity.User;
import com.example.cmcboard.domain.Entity.UserRole;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserToEntity {
    private String loginId;
    private String password;
    private String userName;

    public User toEntity(Long userId, UserRole role) {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .userName(this.userName)
                .role(role)
                .build();
    }
}
