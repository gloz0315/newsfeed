package com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.dto.VoteUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.entity.VoteEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.repository.interfaces.VoteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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
  public Vote findVoteOrElseThrow(Long voteId) {
    return voteJpaRepository.findById(voteId)
        .orElseThrow(() -> new EntityNotFoundException("Vote with id " + voteId + " not found"))
        .toModel();
  }

  public Optional<Vote> findVoteByMemberIdAndPostId(Long memberId, Long postId) {
    return voteJpaRepository.findByMemberIdAndPostId(memberId, postId).map(VoteEntity::toModel);
  }

  public Vote findVoteByMemberIdAndPostIdOrElseThrow(Long memberId, Long postId) {
    return findVoteByMemberIdAndPostId(memberId, postId).orElseThrow(() -> new EntityNotFoundException("Vote not found"));
  }

  @Override
  public List<Vote> findVotesByPostId(Long postId) {
    return voteJpaRepository.findAllByPostId(postId).stream().map(VoteEntity::toModel).toList();
  }

  @Override
  public Vote updateVote(Long voteId, VoteUpdateDto updateDto) {
    VoteEntity voteEntity = voteJpaRepository.findById(voteId)
        .orElseThrow(() -> new EntityNotFoundException("Vote with id " + voteId + " not found"));

    voteEntity.update(updateDto);

    return voteJpaRepository.saveAndFlush(voteEntity).toModel();
  }

  @Override
  public void deleteVote(Long id) {
    VoteEntity voteEntity = voteJpaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vote with id " + id + " not found"));

    voteJpaRepository.delete(voteEntity);
  }

  @Override
  public void deleteVotesByPostId(Long postId) {
    List<VoteEntity> voteEntities = voteJpaRepository.findAllByPostId(postId);

    voteJpaRepository.deleteAll(voteEntities);
  }

  @Override
  public void deleteVotesByMemberId(Long memberId) {
    List<VoteEntity> voteEntities = voteJpaRepository.findAllByMemberId(memberId);

    voteJpaRepository.deleteAll(voteEntities);
  }

  @Override
  public List<Vote> findVotesByMemberId(Long memberId) {
    return voteJpaRepository.findAllByMemberId(memberId).stream().map(VoteEntity::toModel).toList();
  }

}
