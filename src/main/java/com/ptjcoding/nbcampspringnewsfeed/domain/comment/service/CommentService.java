package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import java.util.List;

public interface CommentService {

  Comment createComment(Long memberId, CommentCreateRequestDto requestDto);

  List<Comment> getCommentsByPostId(Long postId);

  List<Comment> getCommentsByMemberIdAndPostId(Long memberId, Long postId);

  Comment updateComment(Long memberId, Long commentId, CommentUpdateRequestDto requestDto);

  void deleteComment(Long memberId, Long commentId);

}
