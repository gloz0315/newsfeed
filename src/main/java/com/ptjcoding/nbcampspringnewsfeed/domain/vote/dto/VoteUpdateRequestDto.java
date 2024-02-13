package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class VoteUpdateRequestDto {

  @NonNull
  @BooleanFlag
  @Max(1)
  @Min(0)
  private Boolean isAgree;

}
