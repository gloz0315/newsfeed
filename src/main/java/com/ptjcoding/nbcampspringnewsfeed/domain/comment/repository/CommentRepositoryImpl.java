package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity.CommentEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  @Override
  public Comment createComment(CommentCreateDto createDto) {
    return commentJpaRepository.save(CommentEntity.of(createDto)).toModel();
  }

  @Override
  public Comment getCommentByCommentId(Long commentId) {
    CommentEntity commentEntity = commentJpaRepository.findById(commentId)
        .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));

    return commentEntity.toModel();
  }

  @Override
  public List<Comment> getCommentsByPostId(Long postId) {
    return commentJpaRepository.findAllByPostId(postId)
        .stream()
        .map(CommentEntity::toModel)
        .toList();
  }

  @Override
  public List<Comment> getCommentsByMemberIdAndPostId(Long memberId, Long postId) {
    return commentJpaRepository.findAllByMemberIdAndPostId(memberId, postId)
        .stream()
        .map(CommentEntity::toModel)
        .toList();
  }

  @Override
  public Comment updatecomment(long id, CommentUpdateDto updateDto) {
    CommentEntity commentEntity = commentJpaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));

    commentEntity.update(updateDto);

    return commentJpaRepository.saveAndFlush(commentEntity).toModel();
  }

  @Override
  public void deleteById(long id) {
    CommentEntity commentEntity = commentJpaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));

    commentJpaRepository.delete(commentEntity);
  }

}
