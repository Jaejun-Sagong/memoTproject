package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentTfDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String content;
    private String memberName;
    private Long heartcnt;

    public CommentTfDto(Comment comment){
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberName = comment.getMemberName();
        this.heartcnt = Long.valueOf(comment.getHeartlist().size());
    }
}
