package com.example.cmcboard.domain.repository;

import com.example.cmcboard.domain.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategory(String category);
}
