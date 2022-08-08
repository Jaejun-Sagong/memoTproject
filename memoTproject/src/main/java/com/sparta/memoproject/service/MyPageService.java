package com.sparta.memoproject.service;


import com.sparta.memoproject.dto.*;
import com.sparta.memoproject.model.Comment;
import com.sparta.memoproject.model.Heart;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private final MemoService memoService;

    @Transactional
    public MyPageDto readPage() {
        int zero =0;
        int one = 1;
        String nickname = memoService.getNickname();
        List<Memo> memoList = memoRepository.findAllByMemberName(nickname);
        List<Comment> commentList = commentRepository.findAllByMemberNameAndParent_IdIsNull(nickname);
        List<Comment> childcommentList = commentRepository.findAllByMemberNameAndParent_IdIsNotNull(nickname);

        List<Heart> heartcheckmemoList = heartRepository.findAllByNicknameAndMemo_IdIsNotNull(nickname);
        List<Memo> heartmemoList = new ArrayList<>();
        for(int i=0; i<heartcheckmemoList.size(); i++){
            heartmemoList.add(heartcheckmemoList.get(i).getMemo());
        }

        List<Heart> heartcheckcommentList = heartRepository.findAllByNicknameAndParent(nickname,zero);
        List<Comment> heartcommentList = new ArrayList<>();
        for(int i=0; i<heartcheckcommentList.size(); i++){
            heartcommentList.add(heartcheckcommentList.get(i).getComment());
        }

        List<Heart> heartcheckchildcommentList = heartRepository.findAllByNicknameAndParent(nickname,one);
        List<Comment> heartchildcommentList = new ArrayList<>();
        for(int i=0; i<heartcheckchildcommentList.size(); i++){
            heartchildcommentList.add(heartcheckchildcommentList.get(i).getComment());
        }

        MyPageDto myPageDto = new MyPageDto();

        for(Memo memo : memoList){
            // 값을넣고 // 값을넣는과정에서 걔가
            MemoTfDto memoTfDto = new MemoTfDto(memo);
            myPageDto.addMomoTfDto(memoTfDto);
            // 찾은값에 set해주기
        }
        for(Comment comment : commentList){
            CommentTfDto commentTfDto = new CommentTfDto(comment);
            myPageDto.addCommentTfDto(commentTfDto);
        }
        for(Comment childComment : childcommentList){
            CommentTfDto commentTfDto = new CommentTfDto(childComment);
            myPageDto.addChildCommentTfDto(commentTfDto);
        }
        for(Memo heartmemo : heartmemoList){
            MemoTfDto memoTfDto = new MemoTfDto(heartmemo);
            myPageDto.addHeartmemoTfDto(memoTfDto);
        }
        for(Comment heartcomment : heartcommentList){
            CommentTfDto commentTfDto = new CommentTfDto(heartcomment);
            myPageDto.addHeartcommentTfDto(commentTfDto);
        }
        for(Comment heartchildComment : heartchildcommentList){
            CommentTfDto commentTfDto = new CommentTfDto(heartchildComment);
            myPageDto.addHeartchildCommentTfDto(commentTfDto);
        }

//        List<Memo> heartmemoList = new ArrayList<>();
//        for(int i=0; i<heartcheckmemoList.size(); i++){
//            heartmemoList.add(heartcheckmemoList.get(i).getMemo());
//        }
//        List<Heart> heartcheckcommentList = heartRepository.findAllByNicknameAndParent(nickname,zero);
//        List<Comment> heartcommentList = new ArrayList<>();
//        for(int i=0; i<heartcheckcommentList.size(); i++){
//            heartcommentList.add(heartcheckcommentList.get(i).getComment());
//        }
//        List<Heart> heartcheckchildcommentList = heartRepository.findAllByNicknameAndParent(nickname,one);
//        List<Comment> heartchildcommentList = new ArrayList<>();
//        for(int i=0; i<heartcheckchildcommentList.size(); i++){
//            heartchildcommentList.add(heartcheckchildcommentList.get(i).getComment());
//        }
//        List<MyPageDto> myPageDtos = new ArrayList<>();
//        for(int i=0; i<memoList.size(); i++){
//            myPageDtos.add(new MyPageDto(memoList.get(i)));
//            myPageDtos.get(i).setMemo(memoList.get(i));
//        }
//        for(int i=0; i<commentList.size(); i++){
//            myPageDtos.get(i).setComment(commentList.get(i));
//        }
//        for(int i=0; i<childcommentList.size(); i++){
//            myPageDtos.get(i).setChildComment(childcommentList.get(i));
//        }
//        for(int i=0; i<heartmemoList.size(); i++){
//            myPageDtos.get(i).setHeartmemo(heartmemoList.get(i));
//        }
//        for(int i=0; i<heartcommentList.size(); i++){
//            myPageDtos.get(i).setHeartcomment(heartcommentList.get(i));
//        }
//        for(int i=0; i<heartchildcommentList.size(); i++){
//            myPageDtos.get(i).setHeartchildComment(heartchildcommentList.get(i));
//        }
        return myPageDto;
    }
}
