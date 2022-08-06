package com.sparta.memoproject.controller;

import com.sparta.memoproject.dto.CommentRequestDto;
import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.service.CommentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/comment")
public class CommentController {

    private final CommentService commentService;  // 필수적인 요소이기 때문에 final 선언


    @Secured("ROLE_USER")
    @PostMapping("/{id}")
    public Comment addComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(id, commentRequestDto);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}/{commentId}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentId, commentRequestDto);
    }
    @Secured("ROLE_USER")
    @DeleteMapping("/{id}/{commentId}")
    public Boolean deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        return commentService.deleteComment(id, commentId);
    }

}

//    @GetMapping("/api/memos")
//    public List<Memo> readMemo(){
//        return memoRepository.findAllByOrderByModifiedAtDesc();
//    }
//}
