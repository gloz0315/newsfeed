package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteResponseDto {

  private final Long voteId;

  private final Long postId;

  private final Boolean isAgree;

  private final LocalDateTime votedDate;

  public static VoteResponseDto of(Vote vote) {
    return new VoteResponseDto(vote.getVoteId(), vote.getPostId(), vote.getIsAgree(), vote.getVotedDate());
  }

}
