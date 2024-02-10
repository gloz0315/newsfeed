package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service.BlackListService;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/blacklists")
@RequiredArgsConstructor
public class BlackListController {

  private final BlackListService blackListService;

  @PostMapping("/register")
  public ResponseEntity<CommonResponseDto<String>> register(
      @RequestAttribute("member") Member member,
      @Validated @RequestBody BlackListRequestDto dto
  ) {
    if (member.getRole().getAuthority().equals(MemberRole.USER.getAuthority())) {
      return CommonResponseDto.badRequest("접근 권한이 없습니다.");
    }

    blackListService.register(dto);
    return CommonResponseDto.ok("블랙리스트에 추가하였습니다.", dto.getEmail());
  }
}
