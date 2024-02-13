package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.MemberUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateDto {

  private String nickname;
  public static MemberUpdateDto of(MemberUpdateRequestDto dto) {
    return new MemberUpdateDto(dto.getNickname());
  }
}
