package com.example.cmcboard.presentation.dto.to;

import com.example.cmcboard.domain.Entity.Comment;
import com.example.cmcboard.domain.Entity.Post;
import com.example.cmcboard.domain.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentToEntity {
    private String content;
    private Long parentId;

    public Comment toEntity(Post post, Comment parent, User user) {
        return Comment.builder()
                .content(this.content)
                .user(user)
                .post(post)
                .parent(parent)
                .build();
    }
}
