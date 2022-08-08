package com.sparta.memoproject.dto;

import com.sparta.memoproject.model.Memo;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class MemoSoloDto {

    private Long id;

    private String title;
    private String contents;

    private String memberName;
    private Long heartNumber;

    public MemoSoloDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.memberName = memo.getMemberName();
        this.heartNumber = Long.valueOf(memo.getHeartList().size());
    }
}
