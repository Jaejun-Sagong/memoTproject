package com.sparta.memoproject.service;

import com.sparta.memoproject.dto.MemoMainResponseDto;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.memoproject.dto.MemoRequestDto;
import com.sparta.memoproject.model.Member;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.CommentRepository;
import com.sparta.memoproject.repository.HeartRepository;
import com.sparta.memoproject.repository.MemberRepository;
import com.sparta.memoproject.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor //final로 선언한 변수가 있으면 꼭 생성해달라는 것
@Service
public class MemoService {


    private final MemoRepository memoRepository; // [2번]update메소드 작성 전에 id에 맞는 값을 찾으려면 find를 써야하는데 find를 쓰기위해서는 Repository가 있어야한다.
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;
    private final CommentRepository commentRepository;

    public List<MemoMainResponseDto> readMemo(){
        List<Memo> memos = memoRepository.findAll();
        List<MemoMainResponseDto> responseDto = new ArrayList<>();
        for (Memo memo : memos) {
            final Long heartCnt = heartRepository.countByMemo(memo);
            responseDto.add(new MemoMainResponseDto(memo, heartCnt));
        }
        return responseDto;
    }

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름


    public String getNickname() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member.get().getNickname();
    }

    @Transactional //업데이트 할 때 이게 DB에 꼭 반영돼야 한다고 해주는 녀석
    public Memo update(Long id, MemoRequestDto requestDto) { //[1번]업데이트 메소드를 선언하고 id와 변경시킬 내용을 담은 녀석이 필요    [6번] return을 보고 반환타입 Long
        Memo memo = memoRepository.findById(id).orElseThrow( //[3번]  수정할 id에 해당하는 데이터를 repo에서 찾고 해당id를 갖는 memo를 호출한다.
                () -> new IllegalArgumentException("메모가 존재하지 않습니다")
        );
        if (!getNickname().equals(memo.getMemberName())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        memo.update(requestDto);
        return memo;

    }

    @Transactional
    public Memo creatMemo(MemoRequestDto requestDto, String nickName, String urlPath)  {
        Memo memo = new Memo(requestDto, nickName, urlPath);
        System.out.println(urlPath);

        memoRepository.save(memo);

        return memo;
    }

    @Transactional
    public boolean delete(long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("메모가 존재하지 않습니다")
        );
        if (!getNickname().equals(memo.getMemberName())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        memoRepository.deleteById(id);
        return true;
    }


}
