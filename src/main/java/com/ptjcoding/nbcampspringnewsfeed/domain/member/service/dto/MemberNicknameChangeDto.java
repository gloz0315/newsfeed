package com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberNicknameChangeDto {

  private String prevName;
  private String afterName;

  public static MemberNicknameChangeDto of(String prevName, String afterName) {
    return MemberNicknameChangeDto.builder()
        .prevName(prevName)
        .afterName(afterName)
        .build();
  }
}
