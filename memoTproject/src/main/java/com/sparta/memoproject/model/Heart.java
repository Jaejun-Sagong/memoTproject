package com.sparta.memoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.memoproject.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity
@Table(name = "heart")
public class Heart extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;

    @Column
    private String nickname;

    @JoinColumn(name = "memo_id")
    @ManyToOne
    @JsonBackReference
    private Memo memo;

    @JoinColumn(name = "comment_id")
    @ManyToOne
    @JsonBackReference
    private Comment comment;

    @Column(name ="Parent_Id")
    private int parent;

    public Heart(String nickname, Memo memo) {
        this.nickname = nickname;
        this.memo = memo;
        this.parent = 2;
    }
    public Heart(String nickname, Comment comment) {
        this.nickname = nickname;
        this.comment = comment;
        if(comment.getParent() != null)
            this.parent = 1;
        else
            this.parent = 0;
    }
}
