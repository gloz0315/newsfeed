package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.CHANGE_NICKNAME;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_EMAIL;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_ID;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_NICKNAME;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_PASSWORD;
import static com.ptjcoding.nbcampspringnewsfeed.test.TestInfo.TEST_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.bookmark.repository.BookmarkRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.hall_of_fame.repository.HallOfFameRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.NicknameUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.NicknameUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.NicknameChangeDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.repository.PostRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberServiceImplTest {

  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PostRepository postRepository;
  @Mock
  private CommentRepository commentRepository;
  @Mock
  private VoteRepository voteRepository;
  @Mock
  private BookmarkRepository bookmarkRepository;
  @Mock
  private HallOfFameRepository hallOfFameRepository;
  @Mock
  private BlackListRepository blackListRepository;
  @Mock
  private JwtProvider jwtProvider;

  @InjectMocks
  private MemberServiceImpl memberService;

  @Nested
  @DisplayName("회원 성공 테스트")
  class SuccessTest {

    @Test
    @DisplayName("회원 가입 성공 테스트")
    void test1() {
      // given
      SignupRequestDto signupRequestDto = new SignupRequestDto();
      ReflectionTestUtils.setField(signupRequestDto, "email", TEST_EMAIL);
      ReflectionTestUtils.setField(signupRequestDto, "nickname", TEST_NICKNAME);
      ReflectionTestUtils.setField(signupRequestDto, "password", TEST_PASSWORD);
      ReflectionTestUtils.setField(signupRequestDto, "checkPassword", TEST_PASSWORD);

      MemberResponseDto resultDto = new MemberResponseDto(TEST_EMAIL,TEST_NICKNAME);

      // when
      MemberResponseDto memberResponseDto = memberService.signup(signupRequestDto);

      // then
      assertEquals(memberResponseDto.getNickname(), resultDto.getNickname());
      assertEquals(memberResponseDto.getEmail(), resultDto.getEmail());
    }

    @Test
    @DisplayName("회원 로그인 성공 테스트")
    void test2() {
      // given
      MockHttpServletResponse response = new MockHttpServletResponse();
      LoginRequestDto dto = new LoginRequestDto("test1@naver.com","Sk123456789");

      String accessToken = "asdf1234";
      String refreshToken = "asdf5678";

      // sub
      given(blackListRepository.checkEmail(dto.getEmail())).willReturn(false);
      given(memberRepository.checkEmail(dto.getEmail())).willReturn(true);
      given(memberRepository.checkPassword(dto)).willReturn(Member.builder().id(1L).build());
      given(jwtProvider.generateAccessToken(1L,"USER")).willReturn(accessToken);
      given(jwtProvider.generateRefreshToken(1L,"USER")).willReturn(refreshToken);

      // when
      memberService.login(dto,response);

      // then
      verify(jwtProvider, atLeastOnce()).addAccessTokenToCookie(accessToken,response);
      verify(jwtProvider, atLeastOnce()).addRefreshTokenToCookie(refreshToken,response);
    }

    @Test
    @DisplayName("회원 정보 조회 성공 테스트")
    void test3() {
      // given
      MockHttpServletRequest request = new MockHttpServletRequest();
      Member member = Member.builder()
          .email(TEST_EMAIL)
          .id(TEST_ID)
          .nickname(TEST_NICKNAME)
          .password(TEST_PASSWORD)
          .role(TEST_ROLE)
          .build();

      // when
      memberService.logout(request,member);

      // then
      verify(jwtProvider, atLeastOnce()).expireToken(request,member.getId());
    }

    @Test
    @DisplayName("회원 이름 변경 성공 테스트")
    void test4() {
      // given
      Member member = Member.builder()
          .id(TEST_ID)
          .email(TEST_EMAIL)
          .nickname(TEST_NICKNAME)
          .password(TEST_PASSWORD)
          .role(TEST_ROLE)
          .build();

      NicknameUpdateRequestDto dto = new NicknameUpdateRequestDto();
      ReflectionTestUtils.setField(dto, "nickname", CHANGE_NICKNAME);

      Member changedMember = Member.builder()
          .nickname(CHANGE_NICKNAME)
          .build();

      given(memberRepository.updateMember(eq(member.getId()), any(NicknameUpdateDto.class))).willReturn(changedMember);

      // when
      NicknameChangeDto nicknameChangeDto = memberService.updateMemberName(member,dto);

      // then
      assertEquals(dto.getNickname(), nicknameChangeDto.getAfterName());
    }

  }


  @Nested
  @DisplayName("회원 실패 테스트")
  class FailTest {

    @Test
    @DisplayName("회원 중복 가입 실패 테스트")
    void test1() {
      // given
      Member member = Member.builder().id(TEST_ID)
          .email(TEST_EMAIL)
          .nickname(TEST_NICKNAME)
          .password(TEST_PASSWORD)
          .role(TEST_ROLE).build();


      // when

      // then
    }
  }

}
