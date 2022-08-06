package com.sparta.memoproject.controller;

import com.sparta.memoproject.dto.MemoMainResponseDto;
import com.sparta.memoproject.dto.MemoRequestDto;
import com.sparta.memoproject.dto.ReadMemoDto;
import com.sparta.memoproject.model.Member;
import com.sparta.memoproject.model.Memo;
import com.sparta.memoproject.repository.MemberRepository;
import com.sparta.memoproject.repository.MemoRepository;
import com.sparta.memoproject.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController // MemoController도 어딘가에서 쓰일 때 new MemoController 이렇게 해서 생성이 되고 사용되어야 하는데 이 어노테이션으로 그 작업을 생략하게 해줌
public class MemoController {  //생성 조회 변경 삭제가 필요한데 업데이트 -> service , 나머지 ->Repo가 필요함

    private final MemoRepository memoRepository;  // 필수적인 요소이기 때문에 final 선언
    private final MemoService memoService;
    private final MemberRepository memberRepository;

    @Secured("ROLE_USER")
    @PostMapping("/api/auth/memos")   //생성은 해당 주소로 post방식으로 들어올것고 그렇게 들어오면 아래를 실행한다.
    public Memo creatMemo(@RequestBody MemoRequestDto requestDto) {   //메모를 생성하려면 데이터를 물고다닐 Dto가 필요하다.  // 날아오는 녀석을 그대로 requestDto에 넣어주기 위해서 해당 어노테이션을 씀

        String nickname = memoService.getNickname();
        Memo memo = memoService.creatMemo(requestDto, nickname);     //Memo에 선언된 오버로딩 생성자로 인해 생성된 memo에 requestDto 내용이 들어감.

        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<MemoMainResponseDto> readMemo() {
        return memoService.readMemo();

    }

    @GetMapping("/api/memos/{id}")
    public Optional<Memo> showMemo(@PathVariable Long id) {
        return memoRepository.findById(id);
    }

    @Secured("ROLE_USER")
    @PutMapping("/api/auth/memos/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다

        return memoService.update(id, requestDto);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/api/auth/memos/{id}")
    public boolean deleteMemo(@PathVariable Long id) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다

        return memoService.delete(id);
    }
}
