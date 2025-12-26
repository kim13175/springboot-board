package com.example.cmcboard.presentation.controller;

import com.example.cmcboard.application.service.PostService;
import com.example.cmcboard.global.security.CustomUserDetail;
import com.example.cmcboard.presentation.dto.from.PostFromDto;
import com.example.cmcboard.presentation.dto.to.PostToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 작성 : /api/posts/create?title=제목&content=내용&authorId=1&category=자유
    @PostMapping("/create")
    public ResponseEntity<PostFromDto> create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Long authorId,
            @RequestParam String category) {

        PostToEntity requestDto = PostToEntity.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();

        PostFromDto response = postService.createPost(requestDto, authorId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 게시글 수정 : /api/posts/update?postId=1&title=수정제목&content=수정내용&category=IT&currentUserId=1
    @PutMapping("/update")
    public ResponseEntity<String> update(
            @RequestParam Long postId,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String category) {

        try {
            PostToEntity updateRequest = PostToEntity.builder()
                    .title(title)
                    .content(content)
                    .category(category)
                    .build();

            postService.updatePost(postId, updateRequest);

            return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // 게시글 삭제: /api/posts/delete?postId=1&currentUserId=1
    @PostMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestParam Long postId,
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        try {
            postService.deletePost(postId, userDetail.getUserId());
            return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    // 북마크 등록 : /api/posts/bookmark?postId=1
    @PostMapping("/bookmark")
    public ResponseEntity<String> toggleBookmark(@RequestParam Long postId) {
        postService.toggleBookmark(postId);
        return ResponseEntity.ok("북마크 등록이 완료되었습니다.");
    }
}
