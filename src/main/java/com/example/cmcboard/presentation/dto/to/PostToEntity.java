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
    private Long authorId;
    private String category;
    private Boolean bookmarked;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .authorId(this.authorId)
                .category(this.category)
                .bookmarked(this.bookmarked)
                .build();
    }
}
