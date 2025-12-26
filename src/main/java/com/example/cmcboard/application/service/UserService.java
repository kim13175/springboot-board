package com.example.cmcboard.application.service;

import com.example.cmcboard.domain.Entity.User;
import com.example.cmcboard.domain.Entity.UserRole;
import com.example.cmcboard.domain.repository.UserRepository;
import com.example.cmcboard.presentation.dto.from.UserFromDto;
import com.example.cmcboard.presentation.dto.to.UserToEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(UserToEntity dto) {
        String encodingPassword = passwordEncoder.encode(dto.getPassword());
        UserRole defaultRole = UserRole.USER;
        User user = dto.toEntity(encodingPassword, defaultRole);
        return userRepository.save(user).getUserId();
    }

    public UserFromDto getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        return UserFromDto.from(user);
    }
}
