package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class BlackListRequestDto {

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;

}
