package com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto;

import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class VoteUpdateRequestDto {

  @NonNull
  @BooleanFlag
  private Boolean isAgree;

}
