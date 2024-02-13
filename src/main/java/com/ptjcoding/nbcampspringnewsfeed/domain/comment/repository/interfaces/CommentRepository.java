package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import java.util.List;

public interface CommentRepository {

  Comment createComment(CommentCreateDto dto);

  Comment findCommentOrElseThrow(Long commentId);

  List<Comment> findCommentsByPostId(Long postId);

  List<Comment> findCommentsByMemberId(Long memberId);

  Comment updateComment(Long commentId, CommentUpdateDto dto);

  void deleteComment(Long commentId);

  void deleteCommentsByPostId(Long postId);

  void deleteCommentsByMemberId(Long memberId);

}
