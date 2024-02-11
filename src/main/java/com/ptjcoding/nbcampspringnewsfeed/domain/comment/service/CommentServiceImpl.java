package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.service.VoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final VoteService voteService;

  private final CommentRepository commentRepository;

  @Override
  public Comment createComment(Long memberId, CommentCreateRequestDto requestDto) {
    voteService.getVoteByMemberIdAndPostId(memberId, requestDto.getPostId());

    CommentCreateDto createDto = CommentCreateDto.builder()
        .content(requestDto.getContent())
        .memberId(memberId)
        .postId(requestDto.getPostId())
        .parentCommentId(requestDto.getParentCommentId())
        .build();

    return commentRepository.createComment(createDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Comment> getCommentsByPostId(Long postId) {
    return commentRepository.getCommentsByPostId(postId);
  }

  @Override
  public List<Comment> getCommentsByMemberIdAndPostId(Long memberId, Long postId) {
    return commentRepository.getCommentsByMemberIdAndPostId(memberId, postId);
  }

  @Override
  public Comment updateComment(Long memberId, Long commentId, CommentUpdateRequestDto requestDto) {
    CommentUpdateDto updateDto = CommentUpdateDto.of(requestDto);

    return commentRepository.updatecomment(commentId, updateDto);
  }

  @Override
  public void deleteComment(Long memberId, Long commentId) {
    commentRepository.deleteById(commentId);
  }

}

