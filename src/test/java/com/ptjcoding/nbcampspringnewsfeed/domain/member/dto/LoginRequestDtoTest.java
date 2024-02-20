package com.ptjcoding.nbcampspringnewsfeed.domain.member.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class LoginRequestDtoTest {

  private static ValidatorFactory factory;
  private static Validator validator;

  @BeforeAll
  public static void init() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Nested
  @DisplayName("실패 테스트")
  class FailTest {

    @Test
    @DisplayName("로그인 RequestDto 이메일 실패 테스트")
    void test1() {
      // given
      LoginRequestDto requestDto = LoginRequestDto.builder()
          .email("qudtn0315naver.com")
          .password("Sk123456")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(requestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("로그인 RequestDto 이메일 공백 테스트")
    void test2() {
      // given
      LoginRequestDto loginRequestDto = LoginRequestDto.builder()
          .password("Sk123456")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("로그인 RequestDto 비밀번호 공백 테스트")
    void test3() {
      // given
      LoginRequestDto loginRequestDto = LoginRequestDto.builder()
          .email("qudtn0315@naver.com")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("로그인 RequestDto 비밀번호 숫자 없이 테스트")
    void test4() {
      // given
      LoginRequestDto loginRequestDto = LoginRequestDto.builder()
          .email("qudtn0315@naver.com")
          .password("Sksksksk")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("로그인 RequestDto 비밀번호 대문자 없이 테스트")
    void test5() {
      // given
      LoginRequestDto loginRequestDto = LoginRequestDto.builder()
          .email("qudtn0315@naver.com")
          .password("sk123456789")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("로그인 RequestDto 비밀번호 소문자 없이 테스트")
    void test6() {
      // given
      LoginRequestDto loginRequestDto = LoginRequestDto.builder()
          .email("qudtn0315@naver.com")
          .password("SK123456789")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(loginRequestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }
  }

  @Nested
  @DisplayName("실패 테스트 반복 추가")
  class FailTestIter {

    @DisplayName("이메일 불가 테스트 반복")
    @ParameterizedTest
    @ValueSource(strings = {"qudtn0315naver.com", "qudtn0315@navercom"})
    void parameterTest1(String string) {
      // given
      LoginRequestDto requestDto = LoginRequestDto.builder()
          .email(string)
          .password("Sk123456")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(requestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertFalse(violations.isEmpty());
    }
  }

  @Nested
  @DisplayName("성공 테스트")
  class SuccessTest {

    @Test
    @DisplayName("로그인 RequestDto 성공 테스트")
    void test10() {
      // given
      LoginRequestDto requestDto = LoginRequestDto.builder()
          .email("qudtn0315@naver.com")
          .password("Sk123456")
          .build();

      // when
      Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(requestDto);

      for (ConstraintViolation<LoginRequestDto> violation : violations) {
        System.err.println(violation.getMessage());
      }

      // then
      assertTrue(violations.isEmpty());
    }
  }
}
