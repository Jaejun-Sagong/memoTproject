package com.sparta.memoproject.image;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final S3Uploader s3Uploader;

    @PostMapping("/images")
    public String upload( MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "static");
        return "업로드 완료";
    }
}
