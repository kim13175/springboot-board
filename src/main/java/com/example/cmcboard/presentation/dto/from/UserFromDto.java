package com.example.cmcboard.presentation.dto.from;

import com.example.cmcboard.domain.Entity.User;
import com.example.cmcboard.domain.Entity.UserRole;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFromDto {
    private Long userId;
    private String loginId;
    private String password;
    private String userName;
    private UserRole role;

    public static UserFromDto from(User user) {
        return UserFromDto.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
