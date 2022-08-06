package com.sparta.memoproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "heart")
@Entity
public class Heart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;
    @Column(nullable = false)
    private String nickname;

    @JoinColumn(name = "memo_id")
    @ManyToOne
    private Memo memo;

    @JoinColumn(name = "comment_id")
    @ManyToOne
    private Comment comment;


    public Heart(String nickname, Memo memo) {
        this.nickname = nickname;
        this.memo = memo;
    }
    public Heart(String nickname, Comment comment) {
        this.nickname = nickname;
        this.comment = comment;
    }

}
