package com.example.cmcboard.application;

import com.example.cmcboard.domain.Entity.Comment;
import com.example.cmcboard.domain.Entity.Post;
import com.example.cmcboard.domain.repository.CommentRepository;
import com.example.cmcboard.domain.repository.PostRepository;
import com.example.cmcboard.presentation.dto.from.CommentFromDto;
import com.example.cmcboard.presentation.dto.to.CommentToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentFromDto createComment(Long postId, CommentToEntity dto, Long authorId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Comment parent = null;
        if (dto.getParentId() != null) {
            parent = commentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("답글을 달 댓글이 존재하지 않습니다."));
        }

        Comment comment = dto.toEntity(post, parent, authorId);
        Comment savedComment = commentRepository.save(comment);

        return CommentFromDto.from(savedComment);
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        comment.update(content);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }

    public List<CommentFromDto> getCommentsByPost(Long postId) {
        return commentRepository.findAllByPostId(postId).stream()
                .map(CommentFromDto::from)
                .collect(Collectors.toList());
    }
}
