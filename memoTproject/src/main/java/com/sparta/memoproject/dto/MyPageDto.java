package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyPageDto {

    private List<MemoTfDto> memoTfDtoList = new ArrayList<>();
    private List<CommentTfDto> commentTfDtoList = new ArrayList<>();
    private List<CommentTfDto> childCommentDtoList = new ArrayList<>();
    private List<MemoTfDto> heartmemoDtoList = new ArrayList<>();
    private List<CommentTfDto> heartcommentDtoList = new ArrayList<>();
    private List<CommentTfDto> heartchildCommentDtoList = new ArrayList<>();

    public void addMomoTfDto(MemoTfDto memoTfDto){
        this.memoTfDtoList.add(memoTfDto);
    }
    public void addCommentTfDto(CommentTfDto commentTfDto){
        this.commentTfDtoList.add(commentTfDto);
    }
    public void addChildCommentTfDto(CommentTfDto childcommentTfDto){
        this.childCommentDtoList.add(childcommentTfDto);
    }
    public void addHeartmemoTfDto(MemoTfDto heartmemoTfDto){
        this.heartmemoDtoList.add(heartmemoTfDto);
    }
    public void addHeartcommentTfDto(CommentTfDto heartcommentTfDto){
        this.heartcommentDtoList.add(heartcommentTfDto);
    }
    public void addHeartchildCommentTfDto(CommentTfDto heartchildcommentTfDto){
        this.heartchildCommentDtoList.add(heartchildcommentTfDto);
    }


}