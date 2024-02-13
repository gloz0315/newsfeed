package com.ptjcoding.nbcampspringnewsfeed.domain.member.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.dto.LoginRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.MemberJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberSignupDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.dto.MemberNicknameUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

  private final MemberJpaRepository memberJpaRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
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
  public Member findMemberOrElseThrow(Long id) {
    return findMember(id).toModel();
  }

  @Override
  public void checkEmail(String email) {
    if (memberJpaRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("해당 이메일이 존재합니다.");
    }
  }

  @Override
  public void deleteMember(Long id) {
    MemberEntity memberEntity = findMember(id);

    memberJpaRepository.delete(memberEntity);
  }

  @Override
  public Member updateMember(Long id, MemberNicknameUpdateDto dto) {
    MemberEntity memberEntity = findMember(id);

    if (memberEntity.getNickname().equals(dto.getNickname())) {
      throw new IllegalArgumentException("이미 해당 닉네임입니다.");
    }

    memberEntity.update(dto);
    return memberEntity.toModel();
  }

  private MemberEntity findMember(Long id) {
    return memberJpaRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException("해당 유저가 존재하지 않습니다.")
    );
  }
}
