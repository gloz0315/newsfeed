package com.ptjcoding.nbcampspringnewsfeed.domain.member.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberService;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<CommonResponseDto<MemberResponseDto>> signup(
      @Validated @RequestBody SignupRequestDto dto
  ) {
    return memberService.signup(dto);
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponseDto<Void>> login(
      @Validated @RequestBody LoginRequestDto dto,
      HttpServletResponse response
  ) {
    return memberService.login(dto, response);
  }

  @PostMapping("/logout")
  public ResponseEntity<CommonResponseDto<Void>> logout(
      HttpServletRequest request
  ) {
    return memberService.logout(request);
  }

  @DeleteMapping("/{memberId}")
  public ResponseEntity<CommonResponseDto<Void>> delete(
      @PathVariable("memberId") Long memberId
  ) {
    return memberService.delete(memberId);
  }
}
