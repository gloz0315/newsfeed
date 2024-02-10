package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.BlackListJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.entity.BlackListEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BlackListRepositoryImpl implements BlackListRepository {

  private final BlackListJpaRepository blackListJpaRepository;

  @Override
  @Transactional
  public void register(String email) {
    if (checkEmail(email)) {
      // TODO: 예외처리에 대한 커스텀은 나중에 한꺼번에 만들 예정
      throw new RuntimeException("해당 이메일이 존재합니다.");
    }

    blackListJpaRepository.save(BlackListEntity.of(email));
  }

  @Override
  public boolean checkEmail(String email) {
    return blackListJpaRepository.findByEmail(email).isPresent();
  }
}
