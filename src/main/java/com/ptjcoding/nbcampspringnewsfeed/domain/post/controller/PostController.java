package com.ptjcoding.nbcampspringnewsfeed.domain.post.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
      @Validated @RequestBody PostRequestDto postRequestDto,
      @RequestAttribute("member") Member member
  ) {

    Post post = postService.createPost(postRequestDto, member.getId());
    return CommonResponseDto.ok("게시글 작성 성공", new PostResponseDto(post));
  }

  @GetMapping
  public ResponseEntity<CommonResponseDto<List<PostResponseDto>>> getPosts() {
    List<PostResponseDto> postResponseDtos = postService
        .getPosts()
        .stream()
        .map(PostResponseDto::new)
        .toList();
    return CommonResponseDto.ok("게시글 전체조회 성공", postResponseDtos);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<CommonResponseDto<PostResponseDto>> updatePost(
      @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto
  ) {
    PostResponseDto postResponseDto = new PostResponseDto(
        postService.updatePost(postId, postRequestDto));
    return CommonResponseDto.ok("게시글 수정 성공", postResponseDto);
  }
}