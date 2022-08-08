package com.sparta.memoproject.dto;


import com.sparta.memoproject.model.Comment;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class CommentDto {

    private Long id;

    private String content;

    private String memberName;

    private Long heartcnt;

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberName = comment.getMemberName();
        this.heartcnt = Long.valueOf(comment.getHeartlist().size());
    }
}
