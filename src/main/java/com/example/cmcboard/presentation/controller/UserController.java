package com.example.cmcboard.presentation.controller;

import com.example.cmcboard.application.service.UserService;
import com.example.cmcboard.global.security.CustomUserDetail;
import com.example.cmcboard.presentation.dto.from.UserFromDto;
import com.example.cmcboard.presentation.dto.to.UserToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserFromDto> getMyInfo(@AuthenticationPrincipal CustomUserDetail userDetail) {
        return ResponseEntity.ok(userService.getMyInfo(userDetail.getUserId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody UserToEntity dto) {
        try {
            Long userId = userService.signUp(dto);
            return ResponseEntity.ok(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
