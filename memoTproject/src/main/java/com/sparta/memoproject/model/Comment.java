package com.sparta.memoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.memoproject.Timestamped;
import com.sparta.memoproject.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String memberName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "MEMO_ID", nullable = false)
    private Memo memo;

//    @Builder
//    public Comment(String author, String content, Authority authority) { //
//        this.author = author;
//        this.content = content;
//        this.authority = authority;
//    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE) //삭제되어도
    private Comment parent; // 4

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> children = new ArrayList<>();


    public Comment(Memo memo, String memberName, CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.memo = memo;
        this.memberName = memberName;
    }

    public Comment(Memo memo, Comment parentComment, CommentRequestDto dto, String memberName) {
        this.memo = memo;
        this.parent = parentComment;
        this.content = dto.getContent();
        this.memberName = memberName;
    }

    public void setComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
