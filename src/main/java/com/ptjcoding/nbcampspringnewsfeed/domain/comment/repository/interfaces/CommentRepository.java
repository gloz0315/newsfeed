package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import java.util.List;

public interface CommentRepository {

  Comment createComment(CommentCreateDto dto);

  List<Comment> getCommentsByPostId(Long postId);

  List<Comment> getCommentsByMemberIdAndPostId(Long memberId, Long postId);

  Comment updatecomment(long id, CommentUpdateDto dto);

  void deleteById(long id);

}
