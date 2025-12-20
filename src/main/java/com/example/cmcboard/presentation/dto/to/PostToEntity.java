package com.example.cmcboard.presentation.dto.to;

import com.example.cmcboard.domain.Entity.Post;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostToEntity {
    private String title;
    private String content;
    private String category;

    public Post toEntity(Long authorId) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .authorId(authorId)
                .category(this.category)
                .bookmarked(false)
                .build();
    }
}
