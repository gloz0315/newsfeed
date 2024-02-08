package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;

public interface CommentService {

  Comment createComment(CommentRequestDto requestDto);

  Comment updateComment(Long commentId, CommentRequestDto requestDto);

  void deleteComment(Long commentId);

}
