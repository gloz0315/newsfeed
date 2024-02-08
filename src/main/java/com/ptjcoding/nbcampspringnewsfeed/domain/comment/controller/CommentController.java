package com.ptjcoding.nbcampspringnewsfeed.domain.comment.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.service.CommentServiceImpl;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final CommentServiceImpl commentService;

  @PostMapping
  public ResponseEntity<CommonResponseDto<CommentResponseDto>> createComment(
      @Validated @RequestBody CommentRequestDto requestDto
  ) {
    Comment responseDto = commentService.createComment(requestDto);

    return CommonResponseDto.ok("댓글 생성 성공", CommentResponseDto.of(responseDto));
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto<CommentResponseDto>> updateComment(
      @Valid @RequestBody CommentRequestDto requestDto, @PathVariable Long commentId
  ) {
    Comment responseDto = commentService.updateComment(commentId, requestDto);

    return CommonResponseDto.ok("댓글 수정 성공", CommentResponseDto.of(responseDto));
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<CommonResponseDto<Object>> deleteComment(
      @PathVariable Long commentId
  ) {
    commentService.deleteComment(commentId);

    return CommonResponseDto.ok("댓글 삭제 성공", null);
  }

}
