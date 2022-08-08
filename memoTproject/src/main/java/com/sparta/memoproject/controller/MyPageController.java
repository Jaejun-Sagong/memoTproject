package com.sparta.memoproject.controller;


import com.sparta.memoproject.dto.MyPageDto;
import com.sparta.memoproject.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class MyPageController {

    private final MyPageService myPageService;

    @Secured("ROLE_USER")
    @GetMapping("/mypages")
    public MyPageDto readPage(
    ) {
        return myPageService.readPage();
    }

}
