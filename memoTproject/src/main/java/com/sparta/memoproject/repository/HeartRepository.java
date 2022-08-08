package com.sparta.memoproject.repository;


import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

//    List<Heart> findAllByAllByNickName(String nickname);
//
//    Heart findByNickname(String nickname);

    Collection<Object> findByMemoAndNickname(Memo memo, String nickname);


    Collection<Object> findByCommentAndNickname(Comment comment, String nickname);

    Heart findByNicknameAndMemo(String nickname, Memo memo);

    Heart findByNicknameAndComment(String nickname, Comment comment);

    Long countByMemo(Memo memo);

    Long countByComment(Comment comment);

    List<Heart> findAllByNicknameAndParent(String nickname, int zero);

    List<Heart> findAllByNicknameAndMemoIdIsNotNull(String nickname);

    List<Heart> findAllByNicknameAndMemo_IdIsNotNull(String nickname);
}
