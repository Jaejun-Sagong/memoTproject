package com.sparta.memoproject.service;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemberRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final MemoService memoService;
    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;


    //사용자가 이미 좋아요 한 게시물인지 체크
    public boolean isAlreadyHeartToMemo(String nickname, Memo memo) {
        return heartRepository.findByNicknameAndMemo(nickname, memo).isPresent();
    }//사용자가 이미 좋아요 한 댓글인지 체크
    private boolean isAlreadyHeartToComment(String nickname, Comment comment) {
        return heartRepository.findByNicknameAndComment(nickname, comment).isPresent();
    }

    //게시글 좋아요
    public Long addHeartToMemo(Long memoId) {
        String nickname = memoService.getNickname();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!isAlreadyHeartToMemo(nickname, memo)) {
            Heart heart = new Heart(nickname, memo);
            heartRepository.save(heart);

        } else {
            Heart heart = heartRepository.findByNicknameAndMemo(nickname, memo).get();
            heartRepository.delete(heart);

        }
        return heartRepository.countByMemo(memo);
    }
    //댓글 좋아요
    public Long addHeartToComment(Long memoId, Long commentId) {
        String nickname = memoService.getNickname();

        if(!memoRepository.findById(memoId).isPresent()){
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");}

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 존재하지 않습니다."));

        if (isAlreadyHeartToComment(nickname, comment)) {
            Heart heart = heartRepository.findByNicknameAndComment(nickname, comment).get();
            heartRepository.delete(heart);
        } else {
            Heart heart = new Heart(nickname, comment);
            heartRepository.save(heart);

        }
        return heartRepository.countByComment(comment);
    }

}