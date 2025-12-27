package com.example.cmcboard.presentation.dto.to;

import com.example.cmcboard.domain.Entity.Post;
import com.example.cmcboard.domain.Entity.User;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostToEntity {
    private String title;
    private String content;
    private String category;

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .category(this.category)
                .bookmarked(false)
                .build();
    }
}
