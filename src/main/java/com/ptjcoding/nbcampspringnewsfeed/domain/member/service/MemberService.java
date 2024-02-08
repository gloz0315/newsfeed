package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

  ResponseEntity<CommonResponseDto<MemberResponseDto>> signup(
      SignupRequestDto signupRequestDto);

  ResponseEntity<CommonResponseDto<Void>> login(LoginRequestDto loginRequestDto,
      HttpServletResponse response);

  ResponseEntity<CommonResponseDto<Void>> logout(HttpServletRequest request);
}
