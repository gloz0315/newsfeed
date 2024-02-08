package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {

  @NotBlank(message = "email은 공백일 수 없음")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "해당 이메일 표현식이 아닙니다.")
  private String email;

  @NotBlank(message = "password는 공백일 수 없습니다.")
  @Pattern(regexp = "^(?!.+ ).+$", message = "paswword는 공백을 포함하면 안됨")
  @Pattern(regexp = "^(?=.*\\d).+$", message = "paswword는 숫자를 포함해야 함")
  @Pattern(regexp = "^(?=.*[a-z]).+$", message = "paswword는 소문자를 포함해야 함")
  @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "paswword는 대문자를 포함해야 함")
  private String password;
}
