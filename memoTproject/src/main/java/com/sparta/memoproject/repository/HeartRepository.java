package com.sparta.memoproject.repository;


import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {


    Optional<Heart> findByNicknameAndMemo(String nickname, Memo memo);

    Long countByMemo(Memo memo);

    Optional<Heart> findByNicknameAndComment(String nickname, Comment comment);

    Long countByComment(Comment comment);

    Long countHeartsByMemoAndNickname(Memo memo, String nickname);
}