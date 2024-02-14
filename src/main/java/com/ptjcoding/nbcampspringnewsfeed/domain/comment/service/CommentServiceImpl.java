package com.ptjcoding.nbcampspringnewsfeed.domain.comment.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.dto.CommentUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final MemberRepository memberRepository;
  private final VoteRepository voteRepository;
  private final PostRepository postRepository;

  private final CommentRepository commentRepository;

  @Override
  public CommentResponseDto createComment(Member member, CommentCreateRequestDto requestDto) {
    Long memberId = member.getId();

    postRepository.findPostOrElseThrow(requestDto.getPostId());
    voteRepository.findVoteByMemberIdAndPostIdOrElseThrow(memberId, requestDto.getPostId());

    Long parentCommentId = requestDto.getParentCommentId();
    if (parentCommentId != null) {
      commentRepository.findCommentOrElseThrow(parentCommentId);
    }

    CommentCreateDto createDto = CommentCreateDto.builder()
        .content(requestDto.getContent())
        .memberId(memberId)
        .postId(requestDto.getPostId())
        .parentCommentId(parentCommentId)
        .build();

    return CommentResponseDto.of(commentRepository.createComment(createDto), member, null);
  }

  @Override
  @Transactional(readOnly = true)
  public CommentResponseDto getCommentByCommentId(Long commentId) {
    Comment comment = commentRepository.findCommentOrElseThrow(commentId);
    Member member = memberRepository.findMemberOrElseThrow(comment.getMemberId());

    return CommentResponseDto.of(comment, member, null);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getCommentsByPostId(Long postId) {
    return commentRepository.findCommentsByPostId(postId)
        .stream()
        .map(comment -> {
          return CommentResponseDto.of(comment,
              memberRepository.findMemberOrElseThrow(comment.getMemberId()),
              null
          );
        }).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getCommentsByMemberId(Long memberId) {
    return commentRepository.findCommentsByMemberId(memberId)
        .stream()
        .map(comment -> CommentResponseDto.of(comment,
            memberRepository.findMemberOrElseThrow(comment.getMemberId()), null))
        .toList();
  }

  @Override
  public CommentResponseDto updateComment(
      Member member, Long commentId, CommentUpdateRequestDto requestDto
  ) {
    validateCommentAndMember(member, commentId);

    CommentUpdateDto updateDto = CommentUpdateDto.of(requestDto);

    return CommentResponseDto.of(commentRepository.updateComment(commentId, updateDto), member,
        null);
  }

  @Override
  public void deleteComment(Member member, Long commentId) {
    validateCommentAndMember(member, commentId);

    commentRepository.deleteComment(commentId);
  }

  @Override
  public void deleteCommentsByPostId(Long postId) {
    commentRepository.deleteCommentsByPostId(postId);
  }

  @Override
  public void deleteCommentsByMemberId(Long memberId) {
    commentRepository.deleteCommentsByMemberId(memberId);
  }

  private void validateCommentAndMember(Member member, Long commentId) {
    Comment comment = commentRepository.findCommentOrElseThrow(commentId);

    boolean isIllegalRequest = !Objects.equals(comment.getMemberId(), member.getId())
                               && member.getRole() == MemberRole.USER;
    if (isIllegalRequest) {
      throw new CustomRuntimeException(GlobalErrorCode.UNAUTHORIZED);
    }
  }

}

