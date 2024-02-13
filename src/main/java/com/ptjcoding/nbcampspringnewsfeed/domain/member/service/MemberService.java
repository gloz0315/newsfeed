package com.ptjcoding.nbcampspringnewsfeed.domain.member.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.MemberUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberChangeDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberInfoDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

  MemberResponseDto signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto, HttpServletResponse response);

  void logout(HttpServletRequest request);

  void delete(Long memberId);

  MemberInfoDto memberInfo(Long memberId);

  MemberChangeDto updateMemberName(Member member, MemberUpdateRequestDto memberUpdateRequestDto);
}
