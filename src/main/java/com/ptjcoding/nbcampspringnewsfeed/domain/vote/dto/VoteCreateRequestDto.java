package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import jakarta.validation.constraints.Positive;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class VoteCreateRequestDto {

  @NonNull
  @Positive
  private Long postId;

  @NonNull
  @BooleanFlag
  private Boolean isAgree;

}
