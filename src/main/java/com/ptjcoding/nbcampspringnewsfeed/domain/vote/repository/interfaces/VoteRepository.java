package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository {

  Vote createVote(VoteCreateDto createDto);

  Vote getVoteByMemberIdAndPostIdOrElseThrow(Long userId, Long postId);

  Vote updateVote(Long voteId, VoteUpdateDto updateDto);

  void deleteVoteById(Long id);

}
