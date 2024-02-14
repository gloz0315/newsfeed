package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NicknameUpdateRequestDto {

  @NotBlank
  @Pattern(regexp = "^(?![^\\s]+[\\s\\W]+$)(?!\\W*[\\s\\W]*$).+$")
  @Size(min = 2, max = 15)
  private String nickname;

}
