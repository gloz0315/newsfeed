package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoteCreateDto {

  private final Boolean isAgree;

  private final Long postId;

}
