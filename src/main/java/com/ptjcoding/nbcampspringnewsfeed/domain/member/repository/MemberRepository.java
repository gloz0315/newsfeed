package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberSignupDto;

public interface MemberRepository {

  void register(MemberSignupDto dto);

  void checkEmail(String email);

  void deleteMember(Long id);

  Member checkPassword(LoginRequestDto dto);

  Member findByIdOrElseThrow(Long id);
}
