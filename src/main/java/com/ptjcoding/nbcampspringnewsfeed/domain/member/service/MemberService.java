package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {

  ResponseEntity<CommonResponseDto<Void>> signup(
      SignupRequestDto signupRequestDto);

  ResponseEntity<CommonResponseDto<Void>> login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
