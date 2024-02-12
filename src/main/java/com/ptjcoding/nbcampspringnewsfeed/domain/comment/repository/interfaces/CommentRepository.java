package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import java.util.List;

public interface CommentRepository {

  Comment createComment(CommentCreateDto dto);

  Comment getCommentByCommentId(Long commentId);

  List<Comment> getCommentsByPostId(Long postId);

  List<Comment> getCommentsByMemberId(Long memberId);

  Comment updateComment(Long commentId, CommentUpdateDto dto);

  void deleteByCommentId(Long commentId);

  void deleteCommentsByMemberId(Long memberId);

}
