package com.example.cmcboard.application.service;

import com.example.cmcboard.domain.Entity.Post;
import com.example.cmcboard.domain.repository.PostRepository;
import com.example.cmcboard.presentation.dto.from.PostFromDto;
import com.example.cmcboard.presentation.dto.to.PostToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // post repo 자동 주입
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostFromDto createPost(PostToEntity request, Long authorId) {
        Post post = postRepository.save(request.toEntity(authorId));
        return PostFromDto.from(post);
    }

    @Transactional
    public void updatePost(Long postId, PostToEntity request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.update(request.getTitle(), request.getContent(), request.getCategory());
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!post.getAuthorId().equals(userId)) {
            throw new RuntimeException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);
    }

    @Transactional
    public void toggleBookmark(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        post.toggleBookmarked();
    }

    public List<PostFromDto> getPostsByCategory(String category) {
        return postRepository.findAllByCategory(category).stream()
                .map(PostFromDto::from)
                .collect(Collectors.toList());
    }
}
