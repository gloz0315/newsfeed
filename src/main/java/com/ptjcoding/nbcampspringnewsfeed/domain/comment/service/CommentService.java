package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import java.util.List;

public interface CommentService {

  CommentResponseDto createComment(Member member, CommentCreateRequestDto requestDto);

  CommentResponseDto getCommentByCommentId(Long commentId);

  List<CommentResponseDto> getCommentsByPostId(Long postId);

  List<CommentResponseDto> getCommentsByMemberId(Long memberId);

  CommentResponseDto updateComment(
      Member member,
      Long commentId,
      CommentUpdateRequestDto requestDto
  );

  void deleteComment(Member member, Long commentId);

  void deleteCommentsByPostId(Long postId);

  void deleteCommentsByMemberId(Long memberId);

}
