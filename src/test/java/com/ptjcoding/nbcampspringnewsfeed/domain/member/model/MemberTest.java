package com.ptjcoding.nbcampspringnewsfeed.domain.member.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class MemberTest {

  private static Member member;

  @Test
  @DisplayName("유저 권한 정보 성공 테스트")
  void test1() {
    // given
    member = Member.builder()
        .id(1L)
        .email("qudtn0315@naver.com")
        .nickname("Gloz")
        .password("Sk1234597")
        .role(MemberRole.USER)
        .build();

    // when, then
    Assertions.assertThat(member.isRole(MemberRole.USER)).isEqualTo(true);
  }

  @Test
  @DisplayName("유저 권한 정보 실패 테스트")
  void test2() {
    // given
    member = Member.builder()
        .id(1L)
        .email("qudtn0315@naver.com")
        .nickname("Gloz")
        .password("Sk1234597")
        .role(MemberRole.USER)
        .build();

    // when, then
    Assertions.assertThat(member.isRole(MemberRole.ADMIN)).isEqualTo(false);
  }
}
