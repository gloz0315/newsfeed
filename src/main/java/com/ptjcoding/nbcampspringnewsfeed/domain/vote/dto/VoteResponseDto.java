package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoteResponseDto {

  private final Long voteId;

  private final String nickname;

  private final Long postId;

  private final Boolean isAgree;

  private final LocalDateTime votedDate;

  public static VoteResponseDto of(Vote vote, Member member) {
    return VoteResponseDto.builder()
        .voteId(vote.getVoteId())
        .nickname(member.getNickname())
        .postId(member.getId())
        .isAgree(vote.getIsAgree())
        .votedDate(vote.getVotedDate())
        .build();
  }

}
