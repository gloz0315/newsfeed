package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class VoteServiceImpl implements VoteService {

  private final VoteRepository voteRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;

  @Override
  public VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto) {
    Long memberId = member.getId();
    Optional<Vote> vote = voteRepository.findVoteByMemberIdAndPostId(memberId, requestDto.getPostId());

    if (vote.isPresent()) {
      throw new RuntimeException("투표가 이미 존재함");
    }

    VoteCreateDto createDto = VoteCreateDto.of(memberId, requestDto);

    String memberNickname = memberRepository.findMemberOrElseThrow(memberId).getNickname();
    return VoteResponseDto.of(voteRepository.createVote(createDto), memberNickname);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Vote> getVoteByMemberIdAndPostId(Long memberId, Long postId) {
    return voteRepository.findVoteByMemberIdAndPostId(memberId, postId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Vote> getVotesByPostId(Long postId) {
    postRepository.findPostOrElseThrow(postId);

    return voteRepository.findVotesByPostId(postId);
  }

  @Override
  public VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto) {
    validateVoteAndMember(member, voteId);

    VoteUpdateDto updateDto = VoteUpdateDto.of(requestDto);
    String memberNickname = memberRepository.findMemberOrElseThrow(member.getId()).getNickname();

    return VoteResponseDto.of(voteRepository.updateVote(voteId, updateDto), memberNickname);
  }

  public void deleteVote(Member member, Long voteId, Boolean isSafe) {
    Vote vote = voteRepository.findVoteOrElseThrow(voteId);
    Long postId = vote.getPostId();

    validateVoteAndMember(member, postId);

    List<Comment> comments = commentRepository.findCommentsByMemberIdAndPostId(member.getId(), postId);

    if (isSafe != null && isSafe && !comments.isEmpty()) {
      throw new RuntimeException("해당 게시글의 댓글을 먼저 삭제해주세요.");
    }

    commentRepository.deleteCommentsByMemberIdAndPostId(member.getId(), postId);
    voteRepository.deleteVote(voteId);
  }

  private void validateVoteAndMember(Member member, Long postId) {
    Vote vote = getVoteByMemberIdAndPostId(member.getId(), postId).orElseThrow(() -> new EntityNotFoundException("Vote not found"));

    boolean isIllegalRequest =
        !Objects.equals(vote.getMemberId(), member.getId()) && member.getRole() == MemberRole.USER;
    if (isIllegalRequest) {
      throw new RuntimeException("접근 권한이 없습니다.");
    }
  }

}
