package com.sparta.memoproject.service;

import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class HeartService {

    private final MemoService memoService;
    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;
    private final HeartRepository heartRepository;

    @Autowired
    public HeartService(
            MemoService memoService,
            CommentRepository commentRepository,
            MemoRepository memoRepository,
            HeartRepository heartRepository
    ){
        this.memoService = memoService;
        this.commentRepository = commentRepository;
        this.heartRepository = heartRepository;
        this.memoRepository = memoRepository;
    }

    @Transactional
    public Long addHeartToMemo(Long memoId) {
        String nickname = getString();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if(heartRepository.findByMemoAndNickname(memo, nickname).isEmpty()){
            Heart heart = new Heart(nickname, memo);
            memo.addHeart(heartRepository.save(heart));
        }
        else {
            heartRepository.delete(heartRepository.findByNicknameAndMemo(nickname, memo));
            memo.deleteHeart(heartRepository.findByNicknameAndMemo(nickname, memo));
        }

        return heartRepository.countByMemo(memo);
    }
    @Transactional
    public Long addHeartToComment(Long commentId) {
        String nickname = getString();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if(heartRepository.findByCommentAndNickname(comment, nickname).isEmpty()){
            Heart heart = new Heart(nickname, comment);
            comment.addHeart(heartRepository.save(heart));
        }
        else {
            heartRepository.delete(heartRepository.findByNicknameAndComment(nickname, comment));
            comment.deleteHeart(heartRepository.findByNicknameAndComment(nickname, comment));
        }

        return heartRepository.countByComment(comment);
    }
    @Transactional
    public Long addHeartToChildComment(Long childcommentId) {
        String nickname = getString();
        Comment comment = commentRepository.findByIdAndParent_IdIsNotNull(childcommentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 대댓글이 존재하지 않습니다."));

        if(heartRepository.findByCommentAndNickname(comment, nickname).isEmpty()){
            Heart heart = new Heart(nickname, comment);
            comment.addHeart(heartRepository.save(heart));
        }
        else {
            heartRepository.delete(heartRepository.findByNicknameAndComment(nickname, comment));
            comment.deleteHeart(heartRepository.findByNicknameAndComment(nickname, comment));
        }

        return heartRepository.countByComment(comment);
    }

    private String getString() {
        String nickname = memoService.getNickname();
        return nickname;
    }
}
