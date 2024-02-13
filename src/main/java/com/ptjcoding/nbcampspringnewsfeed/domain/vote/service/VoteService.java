package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;

public interface VoteService {

  VoteResponseDto createVote(Member member, VoteCreateRequestDto requestDto);

  VoteResponseDto updateVote(Member member, Long voteId, VoteUpdateRequestDto requestDto);

  void deleteVote(Member member, Long voteId, Boolean isSafe);

}
