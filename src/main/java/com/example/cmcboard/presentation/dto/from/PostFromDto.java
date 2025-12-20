package com.example.cmcboard.presentation.dto.from;

import com.example.cmcboard.domain.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostFromDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String category;
    private Boolean bookmarked;

    public static PostFromDto from(Post post) {
        return PostFromDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .category(post.getCategory())
                .bookmarked(post.getBookmarked())
                .build();
    }
}
