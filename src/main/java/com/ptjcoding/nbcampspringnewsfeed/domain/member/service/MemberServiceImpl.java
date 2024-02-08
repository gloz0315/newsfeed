package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import static com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole.USER;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  @Override
  @Transactional
  public ResponseEntity<CommonResponseDto<MemberResponseDto>> signup(
      SignupRequestDto dto
  ) {
    MemberSignupDto memberSignupDto = new MemberSignupDto(dto.getEmail(), dto.getNickname(),
        dto.getPassword(),
        dto.getCheckPassword());

    memberRepository.checkEmail(memberSignupDto.getEmail());
    memberRepository.register(memberSignupDto);

    return CommonResponseDto.ok("회원 가입에 성공하셨습니다.",
        MemberResponseDto.builder()
            .email(dto.getEmail())
            .nickname(dto.getNickname())
            .build());
  }

  @Override
  public ResponseEntity<CommonResponseDto<Void>> login(
      LoginRequestDto dto, HttpServletResponse response
  ) {
    Member member = memberRepository.checkPassword(dto);

    String accessToken = jwtProvider.generateAccessToken(member.getEmail(), USER.getAuthority());
    String refreshToken = jwtProvider.generateRefreshToken(USER.getAuthority());

    jwtProvider.addAccessTokenToCookie(accessToken, response);
    jwtProvider.addRefreshTokenToCookie(refreshToken, response);

    return CommonResponseDto.ok("로그인에 성공하셨습니다.", null);
  }

  @Override
  public ResponseEntity<CommonResponseDto<Void>> logout(HttpServletRequest request) {

    jwtProvider.expireToken(request);

    return CommonResponseDto.ok("로그아웃 하였습니다.", null);
  }
}
