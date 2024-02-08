package com.ptjcoding.nbcampspringnewsfeed.domain.post.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CommonResponseDto<PostResponseDto>> createPost(
      @Validated @RequestBody PostRequestDto postRequestDto
  ) {

    Long memberId = postRequestDto.getMemberId(); // 테스트용 코드 이 후 토큰 검증으로 변경
    Post post = postService.createPost(postRequestDto, memberId);
    return CommonResponseDto.ok("게시글 작성 성공", new PostResponseDto(post));
  }
}
