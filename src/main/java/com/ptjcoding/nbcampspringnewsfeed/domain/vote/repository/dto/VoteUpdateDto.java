package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteUpdateDto {

  private final Boolean isAgree;

  public static VoteUpdateDto of(VoteUpdateRequestDto requestDto) {
    return new VoteUpdateDto(requestDto.getIsAgree());
  }

}
