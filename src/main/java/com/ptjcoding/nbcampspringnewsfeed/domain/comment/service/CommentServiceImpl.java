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
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.service.VoteService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final MemberService memberService;
  private final VoteService voteService;
  private final PostService postService;

  private final CommentRepository commentRepository;

  @Override
  public CommentResponseDto createComment(Member member, CommentCreateRequestDto requestDto) {
    Long memberId = member.getId();

    postService.getPostByPostId(requestDto.getPostId());
    voteService.getVoteByMemberIdAndPostId(memberId, requestDto.getPostId());

    CommentCreateDto createDto = CommentCreateDto.builder()
        .content(requestDto.getContent())
        .memberId(memberId)
        .postId(requestDto.getPostId())
        .parentCommentId(requestDto.getParentCommentId())
        .build();

    return CommentResponseDto.of(commentRepository.createComment(createDto), member);
  }

  @Override
  @Transactional(readOnly = true)
  public CommentResponseDto getCommentByCommentId(Long commentId) {
    Comment comment = commentRepository.getCommentByCommentId(commentId);
    Member member = memberService.getMemberByMemberId(comment.getMemberId());

    return CommentResponseDto.of(comment, member);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getCommentsByPostId(Long postId) {
    return commentRepository.getCommentsByPostId(postId)
        .stream()
        .map(comment -> CommentResponseDto.of(comment, memberService.getMemberByMemberId(comment.getMemberId())))
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentResponseDto> getCommentsByMemberId(Long memberId) {
    return commentRepository.getCommentsByMemberId(memberId)
        .stream()
        .map(comment -> CommentResponseDto.of(comment, memberService.getMemberByMemberId(comment.getMemberId())))
        .toList();
  }

  @Override
  public CommentResponseDto updateComment(
      Member member, Long commentId, CommentUpdateRequestDto requestDto
  ) {
    validateCommentAndMember(member, commentId);

    CommentUpdateDto updateDto = CommentUpdateDto.of(requestDto);

    return CommentResponseDto.of(commentRepository.updateComment(commentId, updateDto), member);
  }

  @Override
  public void deleteComment(Member member, Long commentId) {
    validateCommentAndMember(member, commentId);

    commentRepository.deleteByCommentId(commentId);
  }

  @Override
  public void deleteCommentsByMemberId(Long memberId) {
    commentRepository.deleteCommentsByMemberId(memberId);
  }

  private void validateCommentAndMember(Member member, Long commentId) {
    Comment comment = commentRepository.getCommentByCommentId(commentId);

    boolean isIllegalRequest = !Objects.equals(comment.getMemberId(), member.getId())
                               && member.getRole() == MemberRole.USER;
    if (isIllegalRequest) {
      throw new RuntimeException("접근 권한이 없습니다.");
    }
  }

}

