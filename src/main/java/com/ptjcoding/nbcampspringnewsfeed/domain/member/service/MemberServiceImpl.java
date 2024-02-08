package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.entity.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.entity.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @Override
  @Transactional
  public ResponseEntity<CommonResponseDto<Void>> signup(
      SignupRequestDto signupRequestDto) {
    String email = signupRequestDto.getEmail();
    String password = signupRequestDto.getPassword();
    String checkPassword = signupRequestDto.getCheckPassword();

    if(memberRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("해당 이메일이 존재합니다.");
    }

    if(!password.equals(checkPassword)) {
      throw new IllegalArgumentException("비밀번호가 서로 맞지 않습니다.");
    }

    Member member = Member.builder()
        .email(signupRequestDto.getEmail())
        .nickname(signupRequestDto.getNickname())
        .password(passwordEncoder.encode(signupRequestDto.getPassword()))
        .role(MemberRole.USER)
        .build();

    memberRepository.save(member);

    return CommonResponseDto.ok("회원 가입에 성공하셨습니다.",null);
  }

  @Override
  public ResponseEntity<CommonResponseDto<Void>> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
    String email = loginRequestDto.getEmail();
    String password = loginRequestDto.getPassword();

    Member member = memberRepository.findByEmail(email).orElseThrow(
        () -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다.")
    );

    if(!passwordEncoder.matches(password, member.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER_KEY, jwtUtil.generateToken(member.getEmail(),member.getRole().getAuthority()));

    return CommonResponseDto.ok("로그인에 성공하셨습니다.", null);
  }
}
