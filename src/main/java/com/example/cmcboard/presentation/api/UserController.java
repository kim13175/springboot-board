package com.example.cmcboard.presentation.api;

import com.example.cmcboard.application.service.UserService;
import com.example.cmcboard.global.security.CustomUserDetail;
import com.example.cmcboard.presentation.dto.from.UserFromDto;
import com.example.cmcboard.presentation.dto.to.UserToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserFromDto> getMyInfo(@AuthenticationPrincipal CustomUserDetail userDetail) {
        return ResponseEntity.ok(userService.getMyInfo(userDetail.getUserId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserToEntity dto) {
        try {
            Long userId = userService.signUp(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
