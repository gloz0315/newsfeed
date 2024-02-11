package com.ptjcoding.nbcampspringnewsfeed.domain.post.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.member.model.Member;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.dto.PostRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.PostJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.infrastructure.entity.PostEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.post.model.Post;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
  private final PostJpaRepository postJpaRepository;

  @Override
  @Transactional
  public Post createPost(PostRequestDto postRequestDto, Member member) {
    return postJpaRepository.save(PostEntity.of(postRequestDto, member.getId())).toModel();
  }

  @Override
  public List<Post> getPosts() {
    return postJpaRepository.findAll().stream().map(PostEntity::toModel).toList();
  }

  @Override
  public Post getPost(Long postId) {
    return findByIdOrElseThrow(postId);
  }

  @Override
  public Post findByIdOrElseThrow(Long postId) {
    return postJpaRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException(postId + "번 게시글은 없습니다.")
    ).toModel();
  }
}