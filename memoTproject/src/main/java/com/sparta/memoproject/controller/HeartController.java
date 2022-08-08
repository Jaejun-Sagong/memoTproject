package com.sparta.memoproject.controller;


import com.sparta.memoproject.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/heart")
public class HeartController {

    private final HeartService heartService;

    @Secured("ROLE_USER")
    @PostMapping("/{memoId}")
    public Long addHeartToMemo(@PathVariable Long memoId) {
        return heartService.addHeartToMemo(memoId);
    }

    @Secured("ROLE_USER")
    @PostMapping("/comment/{commentId}")
    public Long addHeartToComment(@PathVariable Long commentId) {
        return heartService.addHeartToComment(commentId);
    }

    @Secured("ROLE_USER")
    @PostMapping("/comment/child/{childcommentId}")
    public Long addHeartToChildComment(@PathVariable Long childcommentId) {
        return heartService.addHeartToChildComment(childcommentId);
    }
}
