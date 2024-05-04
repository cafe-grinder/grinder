package com.grinder.controller;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.enums.ContentType;
import com.grinder.service.AwsS3Service;
import com.grinder.service.implement.AwsS3ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final AwsS3Service awsS3Service;

    // 다운로드 TODO : 안쓰면 지우기
    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file) {

        //  ex. image=https://board-example.s3.ap-northeast-2.amazonaws.com/2b8359b2-de59-4765-8da0-51f5d4e556c3.jpg

        byte[] data = awsS3Service.downloadFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);
    }

    // 테스트용 파일 업로드 컨트롤러 -> TODO : 참고 후 지우기
    // contentId 나 contentType 받는 형식은 자유롭게 하세용
    @PostMapping("/upload/{contentType}/{contentId}")
    public ResponseEntity<SuccessResult> uploadImageList(@RequestPart(value ="imgList")List<MultipartFile>imageList,
                                                         @PathVariable String contentType,
                                                         @PathVariable String contentId){
        ContentType type = ContentType.fromString(contentType);
        if (awsS3Service.uploadImageBucket(imageList,contentId, type)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }


}
