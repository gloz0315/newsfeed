package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository {

  Vote createVote(VoteCreateDto createDto);

  Optional<Vote> findVoteByMemberIdAndPostId(Long memberId, Long postId);

  Vote findVoteOrElseThrow(Long voteId);

  Vote findVoteByMemberIdAndPostIdOrElseThrow(Long memberId, Long postId);

  List<Vote> findVotesByPostId(Long postId);

  Vote updateVote(Long voteId, VoteUpdateDto updateDto);

  void deleteVote(Long voteId);

  void deleteVotesByPostId(Long postId);

  void deleteVotesByMemberId(Long memberId);

}
