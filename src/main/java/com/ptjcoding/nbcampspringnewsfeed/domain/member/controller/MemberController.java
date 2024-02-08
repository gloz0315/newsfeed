package com.ptjcoding.nbcampspringnewsfeed.domain.member.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.SignupRequestDto;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {
	private final MemberServiceImpl memberService;

	@PostMapping("/signup")
	public ResponseEntity<CommonResponseDto<Void>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
		return memberService.signup(signupRequestDto);
	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponseDto<Void>> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
		return memberService.login(loginRequestDto,response);
	}
}
