package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity.CommentEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  @Override
  @Transactional
  public Comment createComment(CommentCreateDto createDto) {
    return commentJpaRepository.save(CommentEntity.of(createDto)).toModel();
  }

  @Override
  @Transactional
  public Comment updatecomment(long id, CommentUpdateDto updateDto) {
    CommentEntity commentEntity = commentJpaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));

    commentEntity.update(updateDto);

    return commentJpaRepository.saveAndFlush(commentEntity).toModel();
  }

  @Override
  @Transactional
  public void deleteById(long id) {
    commentJpaRepository.deleteById(id);
  }

}
