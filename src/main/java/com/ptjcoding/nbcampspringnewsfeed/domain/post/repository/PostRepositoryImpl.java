package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.entity.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.member.repository.MemberRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final MemberRepository memberRepository;
  private final PostJpaRepository postJpaRepository;

  @Override
  public Post createPost(PostRequestDto postRequestDto, Long memberId) {
    Member member = memberRepository.findById(memberId).orElse(null);
    if (member == null) {
      throw new EntityNotFoundException(memberId + " Member not found");
    }
    return postJpaRepository.save(PostEntity.of(postRequestDto, memberId))
        .toModel(member.getNickname());
  }
}
