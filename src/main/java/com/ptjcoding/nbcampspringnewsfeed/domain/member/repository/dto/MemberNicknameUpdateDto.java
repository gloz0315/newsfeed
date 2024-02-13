package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.MemberNicknameUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberNicknameUpdateDto {

  private String nickname;
  public static MemberNicknameUpdateDto of(MemberNicknameUpdateRequestDto dto) {
    return new MemberNicknameUpdateDto(dto.getNickname());
  }
}
