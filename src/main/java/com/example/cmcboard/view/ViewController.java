package com.example.cmcboard.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    @GetMapping("/login")
    public String moveLoginPage() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String moveSignupPage() {
        return "signup";
    }

    @GetMapping("/post")
    public String movePostPage() {
        return "post";
    }
}
