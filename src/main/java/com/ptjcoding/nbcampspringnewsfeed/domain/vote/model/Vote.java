package com.ptjcoding.nbcampspringnewsfeed.domain.vote.model;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Vote {

  private Long voteId;

  private Long postId;

  private Boolean isAgree;

  private LocalDateTime votedDate;

}
