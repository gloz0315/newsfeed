package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import java.util.List;
import java.util.Optional;

public interface VoteService {

  VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto);

  Optional<Vote> getVoteByMemberIdAndPostId(Long memberId, Long postId);

  Vote getVoteByMemberIdAndPostIdOrElseThrow(Long memberId, Long postId);

  List<Vote> getVotesByPostId(Long postId);

  VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto);

  void deleteVote(Member member, Long voteId, Boolean isSafe);

}
