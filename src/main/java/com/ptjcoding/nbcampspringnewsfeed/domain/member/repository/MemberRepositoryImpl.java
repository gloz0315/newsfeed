package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.MemberJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.service.dto.MemberSignupDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberJpaRepository memberJpaRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void register(MemberSignupDto dto) {
    memberJpaRepository.save(MemberEntity.of(
        dto.getEmail(),
        dto.getNickname(),
        passwordEncoder.encode(dto.getPassword()),
        MemberRole.USER
    ));
  }

  @Override
  public Member checkPassword(LoginRequestDto dto) {
    MemberEntity entity = memberJpaRepository.findByEmail(dto.getEmail()).orElseThrow(
        () -> new EntityNotFoundException("해당 이메일이 존재하지 않습니다.")
    );

    if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return entity.toModel();
  }

  @Override
  public Member findByIdOrElseThrow(Long id) {
    return memberJpaRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("해당 유저가 존재하지 않습니다.")
    ).toModel();
  }

  @Override
  public void checkEmail(String email) {
    if (memberJpaRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("해당 이메일이 존재합니다.");
    }
  }
}