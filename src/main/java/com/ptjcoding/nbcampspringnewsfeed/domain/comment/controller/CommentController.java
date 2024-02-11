package com.ptjcoding.nbcampspringnewsfeed.domain.comment.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.service.CommentService;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<CommonResponseDto<CommentResponseDto>> createComment(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody CommentCreateRequestDto requestDto
  ) {
    CommentResponseDto responseDto = commentService.createComment(member, requestDto);

    return CommonResponseDto.ok("댓글 생성 성공", responseDto);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto<CommentResponseDto>> updateComment(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody CommentUpdateRequestDto requestDto,
      @PathVariable Long commentId
  ) {
    CommentResponseDto responseDto = commentService.updateComment(member, commentId, requestDto);

    return CommonResponseDto.ok("댓글 수정 성공", responseDto);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto<String>> deleteComment(
      @RequestAttribute("member") Member member, @PathVariable Long commentId
  ) {
    commentService.deleteComment(member, commentId);

    return CommonResponseDto.ok("댓글 삭제 성공", null);
  }

}
