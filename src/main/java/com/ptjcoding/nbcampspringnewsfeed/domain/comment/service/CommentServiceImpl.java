package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ! TODO: need to verify member before repository calls

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public Comment createComment(CommentCreateRequestDto requestDto) {
    CommentCreateDto createDto = CommentCreateDto.builder()
        .content(requestDto.getContent())
        .memberId(1L)                                // ! TODO: need to add memberId
        .postId(requestDto.getPostId())
        .parentCommentId(requestDto.getParentCommentId())
        .build();

    return commentRepository.createComment(createDto);
  }

  @Override
  @Transactional
  public Comment updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
    CommentUpdateDto updateDto = CommentUpdateDto.of(requestDto);

    return commentRepository.updatecomment(commentId, updateDto);
  }

  @Override
  @Transactional
  public void deleteComment(Long commentId) {
    commentRepository.deleteById(commentId);
  }

}
