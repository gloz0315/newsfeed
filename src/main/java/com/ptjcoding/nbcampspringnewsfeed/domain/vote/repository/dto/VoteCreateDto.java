package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoteCreateDto {

  private final Boolean isAgree;

  private final Long memberId;

  private final Long postId;

  public static VoteCreateDto of(Long memberId, VoteCreateRequestDto requestDto) {
    return new VoteCreateDto(requestDto.getIsAgree(), memberId, requestDto.getPostId());
  }

}
