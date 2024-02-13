package com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberChangeDto {

  private String prevName;
  private String afterName;

  public static MemberChangeDto of(String prevName, String afterName) {
    return MemberChangeDto.builder()
        .prevName(prevName)
        .afterName(afterName)
        .build();
  }
}
