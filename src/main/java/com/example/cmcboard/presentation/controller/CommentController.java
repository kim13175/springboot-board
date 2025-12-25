package com.example.cmcboard.presentation.controller;

import com.example.cmcboard.application.service.CommentService;
import com.example.cmcboard.presentation.dto.from.CommentFromDto;
import com.example.cmcboard.presentation.dto.to.CommentToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성 : /api/comments/create?postId=1&content=내용&authorId=1
    // 답글 생성 : /api/comments/create?postId=1&content=내용&authorId=1&parentId=1
    @PostMapping("/create")
    public ResponseEntity<CommentFromDto> createComment(
            @RequestParam Long postId,
            @RequestParam String content,
            @RequestParam Long authorId,
            @RequestParam(required = false) Long parentId) {

        CommentToEntity dto = CommentToEntity.builder()
                .content(content)
                .parentId(parentId)
                .build();

        return new ResponseEntity<>(commentService.createComment(postId, dto, authorId), HttpStatus.CREATED);
    }

    // 댓글 및 답글 수정: /api/comments/update?commentId=1&content=수정내용
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam Long commentId, @RequestParam String content) {
        commentService.updateComment(commentId, content);
        return ResponseEntity.ok("댓글 수정 완료");
    }

    // 댓글 및 답글 삭제: /api/comments/delete?commentId=1
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
