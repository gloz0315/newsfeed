package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
  @Max(1)
  @Min(0)
  private Boolean isAgree;

}
