package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.entity.VoteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteJpaRepository extends JpaRepository<VoteEntity, Long> {

  Optional<VoteEntity> findByMemberIdAndPostId(Long memberId, Long postId);

  List<VoteEntity> findAllByPostId(Long postId);

  List<VoteEntity> findAllByMemberId(Long memberId);

}
