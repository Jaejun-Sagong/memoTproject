package com.sparta.memoproject.controller;

import com.sparta.memoproject.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/heart")
public class HeartController {

    private final HeartService heartService;  // 필수적인 요소이기 때문에 final 선언

    @Secured("ROLE_USER")
    @PostMapping("/{memoId}")
    public Long addHeartToMemo(@PathVariable Long memoId) {
        return heartService.addHeartToMemo(memoId);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{memoId}/{commentId}")
    public Long addHeartToMemo(@PathVariable Long memoId, @PathVariable Long commentId) {
        return heartService.addHeartToComment(memoId, commentId);
    }
}


//    @Secured("ROLE_USER")
//    @PostMapping("/{memoId}/{commentId}")
//    public Comment addLikeToComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
//        return commentService.addComment(id, commentRequestDto);
//    }
//
//    @Secured("ROLE_USER")
//    @PutMapping("/{id}/{commentId}")
//    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
//        return commentService.updateComment(id, commentId, commentRequestDto);
//    }
//    @Secured("ROLE_USER")
//    @DeleteMapping("/{id}/{commentId}")
//    public Boolean deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
//        return commentService.deleteComment(id, commentId);
//    }
