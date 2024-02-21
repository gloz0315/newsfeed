package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;

  @NotBlank
  @Pattern(regexp = "^(?![^\\s]+[\\s\\W]+$)(?!\\W*[\\s\\W]*$).+$")
  @Size(min = 2, max = 15)
  private String nickname;

  @NotBlank
  @Pattern(regexp = "^(?!.+ ).+$")
  @Pattern(regexp = "^(?=.*\\d).+$")
  @Pattern(regexp = "^(?=.*[a-z]).+$")
  @Pattern(regexp = "^(?=.*[A-Z]).+$")
  private String password;

  @NotBlank
  @Pattern(regexp = "^(?!.+ ).+$")
  @Pattern(regexp = "^(?=.*\\d).+$")
  @Pattern(regexp = "^(?=.*[a-z]).+$")
  @Pattern(regexp = "^(?=.*[A-Z]).+$")
  private String checkPassword;

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    SignupRequestDto dto = (SignupRequestDto) o;
    return Objects.equals(email, dto.email) &&
        Objects.equals(nickname, dto.nickname) &&
        Objects.equals(password, dto.password) &&
        Objects.equals(checkPassword, dto.checkPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, nickname, password, checkPassword);
  }
}
