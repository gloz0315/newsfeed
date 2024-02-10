package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class BlackListRequestDto {

  @NotBlank(message = "email은 공백일 수 없음")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "해당 이메일 표현식이 아닙니다.")
  private String email;

}
