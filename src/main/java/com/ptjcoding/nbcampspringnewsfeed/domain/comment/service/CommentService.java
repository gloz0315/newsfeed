package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import java.util.List;

public interface CommentService {

  Comment createComment(Member member, CommentCreateRequestDto requestDto);

  Comment getCommentByCommentId(Long commentId);

  List<Comment> getCommentsByPostId(Long postId);

  List<Comment> getCommentsByMemberId(Long memberId);

  Comment updateComment(Member member, Long commentId, CommentUpdateRequestDto requestDto);

  void deleteComment(Member member, Long commentId);

  void deleteCommentsByPostId(Long postId);

  void deleteCommentsByMemberId(Long memberId);

}
