package com.ptjcoding.nbcampspringnewsfeed.domain.post.controller;


import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

  PostServiceImpl postService;

  @PostMapping
  public ResponseEntity<CommonResponseDto<PostResponseDto>> createPost(
      @Valid @RequestBody PostRequestDto postRequestDto
  ) {
    return CommonResponseDto.ok("게시글 등록 성공", postService.createPost(postRequestDto));
  }

}
