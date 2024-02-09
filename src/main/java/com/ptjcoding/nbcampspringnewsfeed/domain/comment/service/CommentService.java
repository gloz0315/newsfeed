package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import java.util.List;

public interface CommentService {

  Comment createComment(CommentCreateRequestDto requestDto);

  List<Comment> getCommentsByPostId(Long postId);

  Comment updateComment(Long commentId, CommentUpdateRequestDto requestDto);

  void deleteComment(Long commentId);

}
