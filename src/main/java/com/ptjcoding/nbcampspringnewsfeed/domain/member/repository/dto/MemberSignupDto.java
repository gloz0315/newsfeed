package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto;

import lombok.Getter;

@Getter
public class MemberSignupDto {

  private final String email;
  private final String nickname;
  private final String password;
  private final String checkPassword;

  public MemberSignupDto(String email, String nickname, String password, String checkPassword) {
    checkPasswordEquals(password, checkPassword);
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.checkPassword = checkPassword;
  }

  private void checkPasswordEquals(String password, String checkPassword) {
    if (!password.equals(checkPassword)) {
      throw new IllegalArgumentException("비밀번호가 서로 맞지 않습니다.");
    }
  }
}
