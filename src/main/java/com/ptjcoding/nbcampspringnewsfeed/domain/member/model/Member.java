package com.ptjcoding.nbcampspringnewsfeed.domain.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  private Long id;
  private String email;
  private String nickname;
  private String password;
  private MemberRole role;

  public Member update(String nickname, String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
    }

    return Member.builder()
        .id(id)
        .email(email)
        .nickname(nickname)
        .password(password)
        .role(role)
        .build();
  }

  // TODO: 회원 탈퇴에 쓰일 메서드
  public void delete(String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
    }
  }
}
