package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.BlackListJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.infrastructure.entity.BlackListEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BlackListRepositoryImpl implements BlackListRepository {

  private final BlackListJpaRepository blackListJpaRepository;

  @Override
  public void register(String email) {
    if (checkEmail(email)) {
      // TODO: 예외처리에 대한 커스텀은 나중에 한꺼번에 만들 예정
      throw new RuntimeException("해당 이메일이 존재합니다.");
    }

    blackListJpaRepository.save(BlackListEntity.of(email));
  }

  @Override
  public void deregister(String email) {
    BlackListEntity findEntity = blackListJpaRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("해당 이메일이 존재하지 않습니다."));

    blackListJpaRepository.delete(findEntity);
  }

  @Override
  public boolean checkEmail(String email) {
    return blackListJpaRepository.findByEmail(email).isPresent();
  }
}
