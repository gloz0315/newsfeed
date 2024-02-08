package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteJpaRepository extends JpaRepository<VoteEntity, Integer> {

}
