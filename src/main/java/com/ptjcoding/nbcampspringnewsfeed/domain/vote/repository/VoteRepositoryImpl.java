package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.entity.VoteEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepository {

  private final VoteJpaRepository voteJpaRepository;

  @Override
  public Vote createVote(VoteCreateDto createDto) {
    return voteJpaRepository.save(VoteEntity.of(createDto)).toModel();
  }

  @Override
  public Vote updateVote(Long voteId, VoteUpdateDto updateDto) {
    VoteEntity voteEntity = voteJpaRepository.findById(voteId)
        .orElseThrow(() -> new EntityNotFoundException("Vote with id " + voteId + " not found"));

    voteEntity.update(updateDto);

    return voteJpaRepository.saveAndFlush(voteEntity).toModel();
  }

  @Override
  public void deleteVoteById(Long id) {
    VoteEntity voteEntity = voteJpaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vote with id " + id + " not found"));

    voteJpaRepository.delete(voteEntity);
  }

}
