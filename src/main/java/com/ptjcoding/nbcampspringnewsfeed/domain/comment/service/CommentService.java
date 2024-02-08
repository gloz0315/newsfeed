package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.entity.Comment;

public interface CommentService {

  Comment createComment(CommentRequestDto requestDto);

  Comment updateComment(CommentRequestDto requestDto, Long commentId);

  boolean deleteComment(Long commentId);

}
