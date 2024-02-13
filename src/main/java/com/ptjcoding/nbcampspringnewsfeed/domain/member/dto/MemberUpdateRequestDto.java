package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

  @NotBlank(message = "nickname은 공백일 수 없습니다.")
  @Pattern(regexp = "^(?![^\\s]+[\\s\\W]+$)(?!\\W*[\\s\\W]*$).+$", message = "첫번째나 마지막에 공백문자 및 기호로 끝나지 않습니다.")
  @Size(min = 2, max = 15, message = "nickname은 2자 이상 15자 이하만 가능")
  private String nickname;

}
