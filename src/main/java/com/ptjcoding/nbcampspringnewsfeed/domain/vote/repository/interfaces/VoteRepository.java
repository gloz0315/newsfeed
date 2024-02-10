package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository {

  Vote createVote(VoteCreateDto createDto);

  Optional<Vote> getVoteByMemberIdAndPostId(Long userId, Long postId);

  Vote updateVote(Long voteId, VoteUpdateDto updateDto);

  void deleteVoteById(Long id);

}
