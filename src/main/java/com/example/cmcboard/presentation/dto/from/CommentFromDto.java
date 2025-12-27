package com.example.cmcboard.presentation.dto.from;

import com.example.cmcboard.domain.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommentFromDto {
    private Long id;
    private String content;
    private Long authorId;
    private Long parentId;

    public static CommentFromDto from(Comment comment) {
        return CommentFromDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorId(comment.getUser().getUserId())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .build();
    }
}
