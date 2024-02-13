package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberUpdateDto;

public interface MemberRepository {

  void register(MemberSignupDto dto);

  void checkEmail(String email);

  void deleteMember(Long id);

  Member updateMember(Long id, MemberUpdateDto dto);

  Member checkPassword(LoginRequestDto dto);

  Member findMemberOrElseThrow(Long id);
}
