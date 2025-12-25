package com.example.cmcboard.domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 8)
    private String userName;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false, length = 12)
    private String password;

    @Column(nullable = false, columnDefinition = "admin")
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();
}

