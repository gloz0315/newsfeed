package com.ptjcoding.nbcampspringnewsfeed.domain.vote.model;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Vote {

  private Long voteId;

  private Long postId;

  private Long memberId;

  private Boolean isAgree;

  private LocalDateTime votedDate;

  public boolean checkMemberIdEqual(Long memberId) {
    return Objects.equals(this.memberId, memberId);
  }

  public boolean checkIsAgreeEqual(Boolean isAgree) {
    return Objects.equals(this.isAgree, isAgree);
  }

}
