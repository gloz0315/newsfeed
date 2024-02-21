package com.ptjcoding.nbcampspringnewsfeed.domain.member.controller;

import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_EMAIL;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_FAIL_EMAIL;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_FAIL_PASSWORD;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_NICKNAME;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_PASSWORD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

  MockMvc mockMvc;

  private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
      .plugin(new JakartaValidationPlugin())
      .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)// 이거는 필드리플렉션 인스턴스를 설정해주는 것
      .build();

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private MemberService memberService;

  @Autowired
  WebApplicationContext context;

  @MockBean
  JwtProvider jwtProvider;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .defaultRequest(get("/").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
        .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON))
        .build();
  }

  @Nested
  @DisplayName("회원 가입 테스트")
  class signupTest {
    @Test
    @DisplayName("회원 가입 성공 테스트")
    void test1() throws Exception {
      // given
      SignupRequestDto dto = new SignupRequestDto();

      ReflectionTestUtils.setField(dto, "email", TEST_EMAIL);
      ReflectionTestUtils.setField(dto, "nickname", TEST_NICKNAME);
      ReflectionTestUtils.setField(dto, "password", TEST_PASSWORD);
      ReflectionTestUtils.setField(dto, "checkPassword", TEST_PASSWORD);

      String text = objectMapper.writeValueAsString(dto);

      // when & then
      mockMvc.perform(post("/api/v1/members/signup")
              .content(text))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.status").value("200 OK"))
          .andExpect(jsonPath("$.message").value("create success."))
          .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 이메일 실패 테스트")
    void test2() throws Exception {
      // given
      SignupRequestDto dto = new SignupRequestDto();

      ReflectionTestUtils.setField(dto, "email", TEST_FAIL_EMAIL);
      ReflectionTestUtils.setField(dto, "nickname", TEST_NICKNAME);
      ReflectionTestUtils.setField(dto, "password", TEST_PASSWORD);
      ReflectionTestUtils.setField(dto, "checkPassword", TEST_PASSWORD);

      String text = objectMapper.writeValueAsString(dto);

      // when & then
      mockMvc.perform(post("/api/v1/members/signup")
              .content(text))
          .andExpect(status().is4xxClientError())
          .andExpect(jsonPath("$.status").value("400 BAD_REQUEST"))
          .andExpect(jsonPath("$.message").value("wrong input."))
          .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 닉네임 실패 테스트")
    void test3() throws Exception {
      // given
      SignupRequestDto dto = fixtureMonkey.giveMeBuilder(SignupRequestDto.class)
          .validOnly(false)
          .set("email",TEST_EMAIL)
          .set("nickname", Arbitraries.strings().ofMinLength(16))
          .set("password",TEST_PASSWORD)
          .set("checkPassword",TEST_PASSWORD)
          .sample();

      String text = objectMapper.writeValueAsString(dto);

      // when & then
      mockMvc.perform(post("/api/v1/members/signup")
              .content(text))
          .andExpect(status().is4xxClientError())
          .andExpect(jsonPath("$.status").value("400 BAD_REQUEST"))
          .andExpect(jsonPath("$.message").value("wrong input."))
          .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 비밀번호 실패 테스트")
    void test4() throws Exception {
      // given
      SignupRequestDto dto = new SignupRequestDto();
      ReflectionTestUtils.setField(dto, "email", TEST_EMAIL);
      ReflectionTestUtils.setField(dto, "nickname", TEST_NICKNAME);
      ReflectionTestUtils.setField(dto, "password", TEST_FAIL_PASSWORD);
      ReflectionTestUtils.setField(dto, "checkPassword", TEST_FAIL_PASSWORD);

      String text = objectMapper.writeValueAsString(dto);

      // when & then
      mockMvc.perform(post("/api/v1/members/signup")
              .content(text))
          .andExpect(status().is4xxClientError())
          .andExpect(jsonPath("$.status").value("400 BAD_REQUEST"))
          .andExpect(jsonPath("$.message").value("wrong input."))
          .andDo(print());
    }

  }
}
