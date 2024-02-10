package com.ptjcoding.nbcampspringnewsfeed.domain.member.admin;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.MemberJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.infrastructure.entity.MemberEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.MemberRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

  private final MemberJpaRepository memberJpaRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @PostConstruct
  public void initAdmin() {
    // TODO: 관리자 이메일을 등록하는 곳, 처음 만들면 그 뒤에는 주석처리해줘야함
    /*
    memberJpaRepository.save(MemberEntity.of(
        "qudtn0315@naver.com"
        ,"Admin"
        , passwordEncoder.encode("Sk123456789")
        , MemberRole.ADMIN
    ));
    */
  }
}
