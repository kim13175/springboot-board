package com.example.cmcboard.domain.repository;

import com.example.cmcboard.domain.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Post, Long> {
}
