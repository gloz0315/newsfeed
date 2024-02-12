package com.ptjcoding.nbcampspringnewsfeed.domain.vote.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ! TODO: implement member Id

@RequiredArgsConstructor
@Service
@Transactional
public class VoteServiceImpl implements VoteService {

  private final VoteRepository voteRepository;

  @Override
  public Vote createVote(VoteCreateRequestDto requestDto) {
    VoteCreateDto createDto = VoteCreateDto.of(1L, requestDto); // ! TODO: implement memberId here!

    return voteRepository.createVote(createDto);
  }

  @Override
  public Vote getVoteByMemberIdAndPostId(Long memberId, Long postId) {
    return voteRepository.getVoteByMemberIdAndPostIdOrElseThrow(memberId, postId);
  }

  @Override
  public Vote updateVote(Long voteId, VoteUpdateRequestDto requestDto) {
    VoteUpdateDto updateDto = VoteUpdateDto.of(requestDto);

    return voteRepository.updateVote(voteId, updateDto);
  }

  @Override
  public void deleteVote(Long voteId) {
    voteRepository.deleteVoteById(voteId);
  }

}
