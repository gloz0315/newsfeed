package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.service.PostService;
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

  private final PostService postService;

  private final VoteRepository voteRepository;

  @Override
  public VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto) {
    Long memberId = member.getId();
    Optional<Vote> vote = voteRepository.getVoteByMemberIdAndPostId(memberId, requestDto.getPostId());

    if (vote.isPresent()) {
      throw new RuntimeException("투표가 이미 존재함");
    }

    VoteCreateDto createDto = VoteCreateDto.of(memberId, requestDto);

    return VoteResponseDto.of(voteRepository.createVote(createDto), member);
  }

  @Override
  public Optional<Vote> getVoteByMemberIdAndPostId(Long memberId, Long postId) {
    return voteRepository.getVoteByMemberIdAndPostId(memberId, postId);
  }

  @Override
  public Vote getVoteByMemberIdAndPostIdOrElseThrow(Long memberId, Long postId) {
    Optional<Vote> vote = getVoteByMemberIdAndPostId(memberId, postId);

    if (vote.isEmpty()) {
      throw new EntityNotFoundException("Vote not found");
    }

    return vote.get();
  }

  @Override
  public List<Vote> getVotesByPostId(Long postId) {
    postService.getPostByPostId(postId);

    return voteRepository.getVotesByPostId(postId);
  }

  @Override
  public VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto) {
    validateVoteAndMember(member, voteId);

    VoteUpdateDto updateDto = VoteUpdateDto.of(requestDto);

    return VoteResponseDto.of(voteRepository.updateVote(voteId, updateDto), member);
  }

  @Override
  public void deleteVote(Member member, Long voteId) {
    validateVoteAndMember(member, voteId);

    voteRepository.deleteVoteById(voteId);
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
