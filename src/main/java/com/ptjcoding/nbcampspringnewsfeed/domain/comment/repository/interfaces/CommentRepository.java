package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;

public interface CommentRepository {

  Comment createComment(CommentCreateDto dto);

  Comment updatecomment(long id, CommentUpdateDto dto);

  void deleteById(long id);

}
