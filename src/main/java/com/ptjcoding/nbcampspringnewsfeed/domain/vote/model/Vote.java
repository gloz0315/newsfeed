package com.ptjcoding.nbcampspringnewsfeed.domain.vote.model;

import java.time.LocalDateTime;
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

}
