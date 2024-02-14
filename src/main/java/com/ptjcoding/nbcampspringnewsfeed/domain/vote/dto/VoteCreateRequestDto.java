package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.BooleanFlag;
import lombok.Getter;

@NotNull
@Getter
public class VoteCreateRequestDto {

  @Positive
  private Long postId;

  @BooleanFlag
  private Boolean isAgree;

}
